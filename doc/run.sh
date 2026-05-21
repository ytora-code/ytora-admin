#!/usr/bin/env bash

# ==========================================================
# Java Jar 服务启动脚本
#
# 使用方式：
#   ./run.sh
#   ./run.sh xxx.jar
#   ./run.sh start
#   ./run.sh start xxx.jar
#   ./run.sh stop
#   ./run.sh status
#   ./run.sh restart
#   ./run.sh restart xxx.jar
#
# 说明：
#   1. 不传参数时，默认执行 restart
#   2. 如果第一个参数是 .jar 文件，则默认对该 Jar 执行 restart
#   3. start/restart 后面可以传 jar 路径
#   4. 如果没有传 jar 路径，会使用 DEFAULT_JAR_PATH
#   5. 如果 DEFAULT_JAR_PATH 也为空，则默认使用当前目录第一个 jar 包
#   6. 日志输出到脚本同目录 run.log
#   7. PID 保存到脚本同目录 run.pid
# ==========================================================

set -e

# ==========================================================
# 1. 默认 Jar 路径
#
# 示例：
#   DEFAULT_JAR_PATH="./app.jar"
#   DEFAULT_JAR_PATH="/data/app/admin/admin.jar"
#
# 如果不想配置默认路径，保持为空即可。
# ==========================================================
DEFAULT_JAR_PATH=""

# ==========================================================
# 2. Java 路径 和 启动参数
#
# 示例：
#   JAVA_PATH="java"
#   JAVA_PATH="/home/jdk17/bin/java"
#   JAVA_OPTS="-Xms512m -Xmx512m"
#   JAVA_OPTS="-Xms512m -Xmx512m -Dspring.profiles.active=prod"
# ==========================================================
JAVA_PATH="java"
JAVA_OPTS="-Xms512m -Xmx512m"

# ==========================================================
# 3. 优雅停止等待时间，单位：秒
# ==========================================================
STOP_TIMEOUT=30


# 脚本所在目录
BASE_DIR="$(cd "$(dirname "$0")" && pwd)"

# 日志文件
LOG_FILE="${BASE_DIR}/run.log"

# PID 文件
PID_FILE="${BASE_DIR}/run.pid"

strip_cr() {
  local value="${1:-}"
  value="${value%$'\r'}"
  echo "$value"
}

# 脚本收到的第一个参数
RAW_COMMAND="$(strip_cr "${1:-}")"

# 如果第一个参数为空，也就是脚本调用时没传参数，则默认执行 restart
if [ -z "$RAW_COMMAND" ]; then
  COMMAND="restart"
  INPUT_JAR_PATH=""
# 如果第一个参数内容以".jar"结尾，则对该jar执行 restart
elif [[ "$RAW_COMMAND" == *.jar ]]; then
  COMMAND="restart"
  INPUT_JAR_PATH="$RAW_COMMAND"
# 否则使用指定的 COMMAND 命令和 jar 路径
else
  COMMAND="$RAW_COMMAND"
  INPUT_JAR_PATH="$(strip_cr "${2:-}")"
fi

DEFAULT_JAR_PATH="$(strip_cr "$DEFAULT_JAR_PATH")"
JAVA_PATH="$(strip_cr "$JAVA_PATH")"
JAVA_OPTS="$(strip_cr "$JAVA_OPTS")"
STOP_TIMEOUT="$(strip_cr "$STOP_TIMEOUT")"


# ==========================================================
# 打印信息
# ==========================================================
info() {
  echo "[INFO] $1" >&2
}

warn() {
  echo "[WARN] $1" >&2
}

error() {
  echo "[ERROR] $1" >&2
}


# ==========================================================
# 获取最终 Jar 路径
#
# 优先级：
#   1. 命令行传入的 jar 路径
#   2. 脚本内部 DEFAULT_JAR_PATH
#   3. 如果上面两个都没有指定，则默认使用当前目录下第一个 jar
# ==========================================================
get_jar_path() {
  local jar_path=""

  if [ -n "$INPUT_JAR_PATH" ]; then
    jar_path="$INPUT_JAR_PATH"
  elif [ -n "$DEFAULT_JAR_PATH" ]; then
    jar_path="$DEFAULT_JAR_PATH"
  else
    warn "未指定 Jar 路径，默认查找当前路径下第一个 Jar 包。"

    # --------------------------------------------------
    # 自动查找脚本同目录下的第一个 Jar 包
    #
    # 触发条件：
    #   1. 外部没有传入 Jar 路径
    #   2. DEFAULT_JAR_PATH 也没有配置
    #
    # 查找范围：
    #   只查找 run.sh 同目录下的 *.jar 文件
    #
    # 注意：
    #   如果同目录下有多个 Jar 包，会使用按文件名排序后的第一个。
    #   如果需要精确指定，建议使用：
    #     ./run.sh start your-app.jar
    #   或者配置：
    #     DEFAULT_JAR_PATH="./your-app.jar"
    # --------------------------------------------------
    jar_path="$(find "$BASE_DIR" -maxdepth 1 -type f -name "*.jar" | sort | head -n 1)"

    if [ -z "$jar_path" ]; then
      error "当前路径下未找到任何 Jar 包：$BASE_DIR"
      echo
      echo "请使用："
      echo "  ./run.sh start your-app.jar"
      echo
      echo "或者在脚本中配置："
      echo "  DEFAULT_JAR_PATH=\"/path/to/your-app.jar\""
      exit 1
    fi

    warn "已找到 Jar 包：$(basename "$jar_path")"
  fi

  jar_path="$(strip_cr "$jar_path")"

  # 如果是相对路径，则转换为基于脚本目录的路径
  if [[ "$jar_path" != /* ]]; then
    jar_path="${BASE_DIR}/${jar_path}"
  fi

  echo "$jar_path"
}


# ==========================================================
# 判断进程是否存在
# ==========================================================
is_running() {
  if [ ! -f "$PID_FILE" ]; then
    return 1
  fi

  local pid
  pid="$(cat "$PID_FILE" 2>/dev/null || true)"
  pid="$(strip_cr "$pid")"

  if [ -z "$pid" ]; then
    return 1
  fi

  if ! [[ "$pid" =~ ^[0-9]+$ ]]; then
    return 1
  fi

  if kill -0 "$pid" >/dev/null 2>&1; then
    return 0
  fi

  return 1
}


# ==========================================================
# 获取当前 PID
# ==========================================================
get_pid() {
  if [ -f "$PID_FILE" ]; then
    local pid
    pid="$(cat "$PID_FILE" 2>/dev/null || true)"
    strip_cr "$pid"
  fi
}


# ==========================================================
# 启动服务
# ==========================================================
start() {
  local jar_path
  jar_path="$(get_jar_path)"

  if ! command -v "$JAVA_PATH" >/dev/null 2>&1; then
    error "未找到 Java 命令：$JAVA_PATH"
    exit 1
  fi

  if [ ! -f "$jar_path" ]; then
    error "Jar 文件不存在：$jar_path"
    exit 1
  fi

  if is_running; then
    local pid
    pid="$(get_pid)"
    warn "服务已经在运行，PID：$pid"
    exit 0
  fi

  info "准备启动服务..."
  info "Jar 路径：$jar_path"
  info "日志文件：$LOG_FILE"
  info "PID 文件：$PID_FILE"

  # 创建日志文件
  : > "$LOG_FILE"

  # 启动服务
  nohup "$JAVA_PATH" $JAVA_OPTS -jar "$jar_path" > "$LOG_FILE" 2>&1 &

  local pid=$!
  echo "$pid" > "$PID_FILE"

  sleep 2

  if kill -0 "$pid" >/dev/null 2>&1; then
    info "服务启动成功，PID：$pid"
    info "查看日志：tail -fn $LOG_FILE"

    echo
    echo "==================== 当前日志内容 ===================="
    tail -fn 200 "$LOG_FILE"
    echo "======================================================"
  else
    error "服务启动失败，请查看日志：$LOG_FILE"
    rm -f "$PID_FILE"
    exit 1
  fi
}


# ==========================================================
# 停止服务
# ==========================================================
stop() {
  if ! is_running; then
    warn "服务未运行。"

    if [ -f "$PID_FILE" ]; then
      warn "发现无效 PID 文件，已清理：$PID_FILE"
      rm -f "$PID_FILE"
    fi

    return 0
  fi

  local pid
  pid="$(get_pid)"

  info "准备停止服务，PID：$pid"

  # 优雅停止
  kill "$pid" >/dev/null 2>&1 || true

  local waited=0

  while kill -0 "$pid" >/dev/null 2>&1; do
    if [ "$waited" -ge "$STOP_TIMEOUT" ]; then
      warn "服务在 ${STOP_TIMEOUT}s 内未正常退出，准备强制停止..."
      kill -9 "$pid" >/dev/null 2>&1 || true
      break
    fi

    sleep 1
    waited=$((waited + 1))
  done

  rm -f "$PID_FILE"

  info "服务已停止。"
}


# ==========================================================
# 查看服务状态
# ==========================================================
status() {
  if is_running; then
    local pid
    pid="$(get_pid)"
    info "服务正在运行，PID：$pid"
  else
    warn "服务未运行。"
  fi
}


# ==========================================================
# 重启服务
# ==========================================================
restart() {
  stop
  start
}


# ==========================================================
# 使用帮助
# ==========================================================
usage() {
  echo "用法："
  echo "  ./run.sh"
  echo "  ./run.sh [jar路径]"
  echo "  ./run.sh start [jar路径]"
  echo "  ./run.sh stop"
  echo "  ./run.sh status"
  echo "  ./run.sh restart [jar路径]"
  echo
  echo "说明："
  echo "  不传参数时，默认执行 restart"
  echo "  第一个参数如果是 .jar 文件，则默认对该 Jar 执行 restart"
  echo
  echo "示例："
  echo "  ./run.sh"
  echo "  ./run.sh app.jar"
  echo "  ./run.sh start app.jar"
  echo "  ./run.sh start /data/app/admin/admin.jar"
  echo "  ./run.sh restart app.jar"
}

# ==========================================================
# 命令分发
# ==========================================================
case "$COMMAND" in
  start)
    start
    ;;
  stop)
    stop
    ;;
  status)
    status
    ;;
  restart)
    restart
    ;;
  *)
    usage
    exit 1
    ;;
esac