package thread.dao;

import java.util.HashMap;
import java.util.Map;

import thread.domain.ThreadDO;

public class ExecutorDaoImpl implements ExecutorDao {

    private static Map<String, ThreadDO> threadMaps = new HashMap<String, ThreadDO>();

    @Override
    public ThreadDO selectExecutor(String threadId) {
        return threadMaps.get(threadId);
    }

    @Override
    public void insertExecutor(ThreadDO xpThread) {
        threadMaps.put(xpThread.getThreadId(), xpThread);
    }

}
