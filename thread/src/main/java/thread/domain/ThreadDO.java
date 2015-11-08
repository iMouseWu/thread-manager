package thread.domain;

import thread.ExecutorTask;
import thread.constants.ThreadStatus;

/**
 * 线程实体接口
 *
 * @author wuhao
 */
public interface ThreadDO extends ExecutorTask{


    /**
     * 获取线程状态
     *
     * @return
     */
    ThreadStatus getThreadStatus();

    /**
     * 获取重试次数
     *
     * @return
     */
    int getRetryTime();

    /**
     * 查看下次执行时间
     * @return
     */
    long getExecuteTime();



}
