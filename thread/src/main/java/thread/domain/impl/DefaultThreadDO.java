package thread.domain.impl;

import thread.domain.ThreadDO;

/**
 * @author wuhao
 */
public class DefaultThreadDO implements ThreadDO {

    private String threadName;

    private String threadId;

    private String ip;

    @Override
    public String getThreadName() {
        return threadName;
    }

    @Override
    public String getIp() {
        return null;
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
