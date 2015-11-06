package thread.dao;

import java.util.HashMap;
import java.util.Map;

import thread.constants.ThreadStatus;
import thread.domain.ThreadDO;

public class ExecutorTaskDaoImpl implements ExecutorTaskDao {

    private static Map<String, ThreadDO> threadMaps = new HashMap<>();

    @Override
    public ThreadDO selectExecutorTask(String threadId, String threadIp) {
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
    public boolean updateExcutorTaskStatus(String threadId, String ip, ThreadStatus origthreadStatus, ThreadStatus desthreadStatus) {
        return false;
    }

    @Override
    public boolean updateExcutorTaskStatusAndRetryTime(String threadId, String ip, ThreadStatus origthreadStatus, ThreadStatus desthreadStatus) {
        return false;
    }


}
