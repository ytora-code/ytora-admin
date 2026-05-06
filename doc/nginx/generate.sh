#!/usr/bin/env bash

set -e

# ==========================================================
# Nginx 配置生成脚本
#
# 作用：
#   读取 configs/*.properties 配置文件
#   使用 nginx.template.conf 作为模板
#   自动生成 output/*.conf 文件
#
# 目录结构：
#   nginx.template.conf
#   generate.sh
#   configs/admin.properties
#   configs/monitor.properties
#   output/admin.conf
#   output/monitor.conf
# ==========================================================
BASE_DIR="$(cd "$(dirname "$0")" && pwd)"

TEMPLATE_FILE="${BASE_DIR}/nginx.template.conf"
CONFIG_DIR="${BASE_DIR}/configs"
OUTPUT_DIR="${BASE_DIR}/output"
CONFIG_SUFFIX="properties"

VARS='
${LISTEN_PORT}
${SERVER_NAME}
${CLIENT_MAX_BODY_SIZE}
${CLIENT_BODY_BUFFER_SIZE}
${API_PREFIX}
${BACKEND_CONTEXT_PATH}
${BACKEND_ORIGIN}
${FRONTEND_BASE_PATH}
${FRONTEND_DIST_PATH}
'

REQUIRED_KEYS=(
  LISTEN_PORT
  SERVER_NAME
  CLIENT_MAX_BODY_SIZE
  CLIENT_BODY_BUFFER_SIZE
  API_PREFIX
  BACKEND_CONTEXT_PATH
  BACKEND_ORIGIN
  FRONTEND_BASE_PATH
  FRONTEND_DIST_PATH
)

info() {
  echo "[INFO] $1"
}

warn() {
  echo "[WARN] $1"
}

error() {
  echo "[ERROR] $1"
}

trim() {
  local value="$1"

  # 去掉 Windows 行尾 \r
  value="${value%$'\r'}"

  # 去掉首尾空白
  value="${value#"${value%%[![:space:]]*}"}"
  value="${value%"${value##*[![:space:]]}"}"

  echo "$value"
}

clear_vars() {
  unset LISTEN_PORT
  unset SERVER_NAME
  unset CLIENT_MAX_BODY_SIZE
  unset CLIENT_BODY_BUFFER_SIZE
  unset API_PREFIX
  unset BACKEND_CONTEXT_PATH
  unset BACKEND_ORIGIN
  unset FRONTEND_BASE_PATH
  unset FRONTEND_DIST_PATH
}

load_properties() {
  local file="$1"
  local line_no=0

  while IFS= read -r line || [ -n "$line" ]; do
    line_no=$((line_no + 1))

    # 去掉 Windows 行尾 \r
    line="${line%$'\r'}"

    # 去掉首尾空白
    line="$(trim "$line")"

    # 跳过空行
    if [ -z "$line" ]; then
      continue
    fi

    # 跳过注释行
    if [[ "$line" == \#* ]]; then
      continue
    fi

    # 必须包含 =
    if [[ "$line" != *=* ]]; then
      error "配置格式错误：$file 第 ${line_no} 行不是 key=value 格式"
      echo "错误内容：$line"
      exit 1
    fi

    local key="${line%%=*}"
    local value="${line#*=}"

    key="$(trim "$key")"
    value="$(trim "$value")"

    if [ -z "$key" ]; then
      error "配置格式错误：$file 第 ${line_no} 行 key 为空"
      exit 1
    fi

    # 只允许合法变量名，避免奇怪内容被 export
    if [[ ! "$key" =~ ^[A-Za-z_][A-Za-z0-9_]*$ ]]; then
      error "配置项名称不合法：$file 第 ${line_no} 行：$key"
      echo "配置项只能使用字母、数字、下划线，并且不能以数字开头"
      exit 1
    fi

    export "$key=$value"
  done < "$file"
}

check_required_keys() {
  local filename="$1"

  for key in "${REQUIRED_KEYS[@]}"; do
    if [ -z "${!key:-}" ]; then
      error "${filename} 缺少 ${key}"
      exit 1
    fi
  done
}

check_format() {
  local filename="$1"

  if [[ "$API_PREFIX" != /* ]]; then
    error "${filename} 的 API_PREFIX 必须以 / 开头，例如：/api"
    exit 1
  fi

  if [[ "$API_PREFIX" == */ ]]; then
    error "${filename} 的 API_PREFIX 不要以 / 结尾，例如：/api，不要写 /api/"
    exit 1
  fi

  if [[ "$BACKEND_CONTEXT_PATH" != /* ]]; then
    error "${filename} 的 BACKEND_CONTEXT_PATH 必须以 / 开头，例如：/system"
    exit 1
  fi

  if [[ "$BACKEND_CONTEXT_PATH" == */ ]]; then
    error "${filename} 的 BACKEND_CONTEXT_PATH 不要以 / 结尾，例如：/system，不要写 /system/"
    exit 1
  fi

  if [[ "$BACKEND_ORIGIN" == */ ]]; then
    error "${filename} 的 BACKEND_ORIGIN 不要以 / 结尾，例如：http://127.0.0.1:9000"
    exit 1
  fi

  if [[ "$FRONTEND_BASE_PATH" != /* ]]; then
    error "${filename} 的 FRONTEND_BASE_PATH 必须以 / 开头，例如：/ 或 /admin/"
    exit 1
  fi

  if [[ "$FRONTEND_DIST_PATH" == */ ]]; then
    warn "${filename} 的 FRONTEND_DIST_PATH 末尾建议不要加 /"
  fi
}

if ! command -v envsubst >/dev/null 2>&1; then
  error "未找到 envsubst 命令，请先安装 gettext。"
  echo "CentOS/RHEL：yum install -y gettext"
  echo "Ubuntu/Debian：apt install -y gettext-base"
  exit 1
fi

if [ ! -f "$TEMPLATE_FILE" ]; then
  error "模板文件不存在：$TEMPLATE_FILE"
  exit 1
fi

if [ ! -d "$CONFIG_DIR" ]; then
  error "配置目录不存在：$CONFIG_DIR"
  exit 1
fi

mkdir -p "$OUTPUT_DIR"

FOUND=0

for CONFIG_FILE in "$CONFIG_DIR"/*."$CONFIG_SUFFIX"; do
  if [ ! -f "$CONFIG_FILE" ]; then
    continue
  fi

  FOUND=1

  CONFIG_FILENAME="$(basename "$CONFIG_FILE")"
  PROJECT_NAME="${CONFIG_FILENAME%.$CONFIG_SUFFIX}"
  OUTPUT_FILE="${OUTPUT_DIR}/${PROJECT_NAME}.conf"

  info "正在生成：${CONFIG_FILENAME} -> ${PROJECT_NAME}.conf"

  clear_vars
  load_properties "$CONFIG_FILE"
  check_required_keys "$CONFIG_FILENAME"
  check_format "$CONFIG_FILENAME"

  envsubst "$VARS" < "$TEMPLATE_FILE" > "$OUTPUT_FILE"

  info "生成完成：$OUTPUT_FILE"
done

if [ "$FOUND" -eq 0 ]; then
  error "没有找到任何配置文件：${CONFIG_DIR}/*.${CONFIG_SUFFIX}"
  exit 1
fi

info "全部配置生成完成，输出目录：$OUTPUT_DIR"