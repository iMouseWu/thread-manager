package thread.dao;

import thread.domain.ThreadDO;
import thread.domain.impl.DefaultThreadDO;

public interface ExecutorTaskDao {

    ThreadDO selectExecutorTask(String threadId, String threadIp);

    ThreadDO selectExecutorTask(String threadId);

    boolean insertExecutorTask(ThreadDO xpThread);

    boolean deleteExcutorTask(String threadId, String ip);

    boolean deleteExcutorTask(String threadId);


}
