package thread.dao;

import java.util.HashMap;
import java.util.Map;

import thread.domain.ThreadDO;

public class ExecutorTaskDaoImpl implements ExecutorTaskDao {

    private static Map<String, ThreadDO> threadMaps = new HashMap<>();

    @Override
    public ThreadDO selectExecutorTask(String threadId, String threadIp) {
        return null;
    }

    @Override
    public ThreadDO selectExecutorTask(String threadId) {
        return null;
    }

    @Override
    public boolean insertExecutorTask(ThreadDO xpThread) {
        return false;
    }

    @Override
    public boolean deleteExcutorTask(String threadId, String ip) {
        return false;
    }

    @Override
    public boolean deleteExcutorTask(String threadId) {
        return false;
    }


}
