package thread;

import thread.domain.TaskProperty;
import thread.domain.ThreadDO;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author wuhao
 */
public class ExceptionRetryTask implements Delayed, ExecutorTask {

    private ThreadDO threadDO;

    public ExceptionRetryTask(ThreadDO threadDO) {
        this.threadDO = threadDO;
    }

    public void setThreadDO(ThreadDO threadDO) {
        this.threadDO = threadDO;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return threadDO.getExecuteTime() - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        if (!(o instanceof ExceptionRetryTask)) {
            return 0;
        }
        ExceptionRetryTask exceptionRetryTask = (ExceptionRetryTask) o;
        long otherExecuteTime = exceptionRetryTask.threadDO.getExecuteTime();
        long selfExecuteTime = this.threadDO.getExecuteTime();
        if (selfExecuteTime > otherExecuteTime) {
            return 1;
        } else if (selfExecuteTime == otherExecuteTime) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public String getTaskId() {
        return threadDO.getTaskId();
    }

    @Override
    public String getIp() {
        return threadDO.getIp();
    }

    @Override
    public String getTaskName() {
        return threadDO.getTaskName();
    }

    @Override
    public void execute() {
        threadDO.execute();
    }

    @Override
    public TaskProperty getTaskProperty() {
        return threadDO.getTaskProperty();
    }
}
