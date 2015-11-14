package thread.dao;

import java.util.concurrent.ConcurrentHashMap;

import thread.constants.ThreadStatus;
import thread.domain.ThreadDO;

public class ExecutorTaskDaoImpl implements ExecutorTaskDao {

	private static ConcurrentHashMap<String, ThreadDO> threadMaps = new ConcurrentHashMap<>();

	private final static String SPILIT = "_";

	@Override
	public ThreadDO selectExecutorTask(String threadId, String threadIp) {
		ThreadDO threadDO = null;
		synchronized (threadMaps) {
			threadDO = threadMaps.get(generateThreadKey(threadId, threadIp));
			if (null != threadDO && threadDO.getThreadStatus() == ThreadStatus.DELETE) {
				threadDO = null;
			}
		}
		return threadDO;
	}

	@Override
	public boolean insertExecutorTask(ThreadDO thread) {
		String threadKey = generateThreadKey(thread.getTaskId(), thread.getIp());
		ThreadDO returnThread = threadMaps.put(threadKey, thread);
		return true;
	}

	@Override
	public boolean deleteExcutorTask(String threadId, String ip) {
		threadMaps.remove(generateThreadKey(threadId, ip));
		return true;
	}

	@Override
	public boolean updateExcutorTaskStatus(String threadId, String ip, ThreadStatus origthreadStatus,
			ThreadStatus desthreadStatus) {
		String threadKey = generateThreadKey(threadId, ip);
		ThreadDO threadDO = threadMaps.get(threadKey);
		synchronized (threadMaps) {
			if (threadDO.getThreadStatus() == origthreadStatus) {
				threadDO.setThreadStatus(desthreadStatus);
				threadMaps.put(threadKey, threadDO);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean updateExcutorTaskStatusAndRetryTime(String threadId, String ip, ThreadStatus origthreadStatus,
			ThreadStatus desthreadStatus) {
		String threadKey = generateThreadKey(threadId, ip);
		ThreadDO threadDO = threadMaps.get(threadKey);
		synchronized (threadMaps) {
			if (threadDO.getThreadStatus() == origthreadStatus) {
				threadDO.setThreadStatus(desthreadStatus);
				threadDO.setRetryTime(threadDO.getRetryTime() + 1);
				threadMaps.put(threadKey, threadDO);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean updateExcutorTaskStatusAndExecuteTime(String threadId, String ip, ThreadStatus origthreadStatus,
			ThreadStatus desthreadStatus, long executeTime) {
		String threadKey = generateThreadKey(threadId, ip);
		ThreadDO threadDO = threadMaps.get(threadKey);
		synchronized (threadMaps) {
			if (threadDO.getThreadStatus() == origthreadStatus) {
				threadDO.setThreadStatus(desthreadStatus);
				threadDO.setExecuteTime(executeTime);
				threadMaps.put(threadKey, threadDO);
				return true;
			}
		}
		return false;
	}

	private String generateThreadKey(String threadId, String threadIp) {
		return threadId + SPILIT + threadIp;
	}

}
