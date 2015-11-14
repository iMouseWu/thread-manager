package configuration.domain;

/**
 * 优先级线程池配置
 *
 * @author wuhao
 */
public class PriorityPoolConfig {

    /**
     * 线程优先级ID
     */
    private int taskPriority;

    /**
     * 线程数量
     */
    private int threadCount;

    public int getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(int taskPriority) {
        this.taskPriority = taskPriority;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }

}
