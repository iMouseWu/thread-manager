package thread.domain.impl;

import thread.ExecutorTask;
import thread.constants.ThreadStatus;
import thread.domain.ThreadDO;

/**
 * @author wuhao
 */
public class DefaultThreadDO implements ThreadDO {

    private ExecutorTask executorTask;

    private ThreadStatus threadStatus;

    private int retryTime;

    public DefaultThreadDO() {

    }

    public DefaultThreadDO(ExecutorTask executorTask) {
        this.executorTask = executorTask;
    }

    @Override
    public String getThreadName() {
        return executorTask.getTaskName();
    }

    @Override
    public String getIp() {
        return executorTask.getIp();
    }

    @Override
    public ThreadStatus getThreadStatus() {
        return threadStatus;
    }

    @Override
    public int getRetryTime() {
        return retryTime;
    }

    @Override
    public long getExecuteTime() {
        return 0;
    }

    @Override
    public void execute() {
        executorTask.execute();;
    }

    public void setThreadStatus(ThreadStatus threadStatus) {
        this.threadStatus = threadStatus;
    }

    @Override
    public String getThreadId() {
        return executorTask.getTaskId();
    }

    public void setExecutorTask(ExecutorTask executorTask) {
        this.executorTask = executorTask;
    }
}
