package thread.domain.impl;

import thread.constants.ThreadStatus;
import thread.domain.ThreadDO;

/**
 * @author wuhao
 */
public class DefaultThreadDO implements ThreadDO {

    private String threadName;

    private String threadId;

    private String ip;

    private ThreadStatus threadStatus;

    private int retryTime;

    @Override
    public String getThreadName() {
        return threadName;
    }

    @Override
    public String getIp() {
        return ip;
    }

    @Override
    public ThreadStatus getThreadStatus() {
        return threadStatus;
    }

    @Override
    public int getRetryTime() {
        return 0;
    }

    public void setRetryTime(int retryTime) {
        this.retryTime = retryTime;
    }

    public void setThreadStatus(ThreadStatus threadStatus) {
        this.threadStatus = threadStatus;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

}
