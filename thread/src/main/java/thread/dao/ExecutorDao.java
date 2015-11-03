package thread.dao;

import thread.domain.ThreadDO;

public interface ExecutorDao {

    ThreadDO selectExecutor(String threadId);

    void insertExecutor(ThreadDO xpThread);

}
