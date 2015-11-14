package thread.domain;

import thread.ExecutorTask;
import thread.constants.ThreadStatus;

/**
 * 线程实体接口
 *
 * @author wuhao
 */
public interface ThreadDO extends ExecutorTask {

    /**
     * 获取线程状态
     *
     * @return
     */
    ThreadStatus getThreadStatus();

    /**
     * 设置线程状态
     *
     * @param threadStatus 线程状态
     */
    void setThreadStatus(ThreadStatus threadStatus);

    /**
     * 获取重试次数
     *
     * @return
     */
    int getRetryTime();

    /**
     * 设置重试次数
     *
     * @param retryTime 新的重试次数
     */
    void setRetryTime(int retryTime);

    /**
     * 查看下次执行时间
     *
     * @return
     */
    long getExecuteTime();

    /**
     * 设置下次执行的时间
     *
     * @param executeTime
     */
    void setExecuteTime(long executeTime);


}
