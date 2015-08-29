package thread.dao;

import java.util.HashMap;
import java.util.Map;

import thread.domain.Thread;

public class ExecutorDaoImpl implements ExecutorDao {

	private static Map<String, Thread> threadMaps = new HashMap<String, Thread>();

	@Override
	public Thread selectExecutor(String threadId) {
		return threadMaps.get(threadId);
	}

	@Override
	public void insertExecutor(Thread xpThread) {
		threadMaps.put(xpThread.getThreadId(), xpThread);
	}

}
