package configuration.domain;

public class GroupPoolConfig {

    /**
     * 任务组别ID
     */
    private int groupId;

    /**
     * 数量
     */
    private int threadCount;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }
}
