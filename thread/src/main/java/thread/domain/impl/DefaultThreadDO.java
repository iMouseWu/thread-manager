package thread.domain.impl;

import thread.ExecutorTask;
import thread.constants.ThreadStatus;
import thread.domain.TaskProperty;
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
    public String getTaskId() {
        return executorTask.getTaskId();
    }

    @Override
    public String getIp() {
        return executorTask.getIp();
    }

    @Override
    public String getTaskName() {
        return executorTask.getTaskName();
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
        executorTask.execute();
    }

    @Override
    public TaskProperty getTaskProperty() {
        return null;
    }

    public void setThreadStatus(ThreadStatus threadStatus) {
        this.threadStatus = threadStatus;
    }

    public void setExecutorTask(ExecutorTask executorTask) {
        this.executorTask = executorTask;
    }
}
