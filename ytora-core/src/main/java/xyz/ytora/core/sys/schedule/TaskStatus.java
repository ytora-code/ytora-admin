package xyz.ytora.core.sys.schedule;

/**
 * 任务状态
 *
 * @author ytora
 * @since 1.0
 */
public enum TaskStatus {

    RUNNING((byte) 1),
    STOPPED((byte) 2);

    private final byte status;

    TaskStatus(byte status) {
        this.status = status;
    }

    public byte status() {
        return status;
    }
}
