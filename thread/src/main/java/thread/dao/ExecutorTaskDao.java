package thread.dao;

import thread.constants.ThreadStatus;
import thread.domain.ThreadDO;

public interface ExecutorTaskDao {

    /**
     * 查询任务(除掉完成状态的)
     *
     * @param threadId
     * @param threadIp
     * @return
     */
    ThreadDO selectExecutorTask(String threadId, String threadIp);

    boolean insertExecutorTask(ThreadDO xpThread);

    boolean deleteExcutorTask(String threadId, String ip);

    boolean updateExcutorTaskStatus(String threadId, String ip, ThreadStatus origthreadStatus, ThreadStatus desthreadStatus);


    boolean updateExcutorTaskStatusAndRetryTime(String threadId, String ip, ThreadStatus origthreadStatus, ThreadStatus desthreadStatus);

    boolean updateExcutorTaskStatusAndExecuteTime(String threadId, String ip, ThreadStatus origthreadStatus, ThreadStatus desthreadStatus,long executeTime);


}
