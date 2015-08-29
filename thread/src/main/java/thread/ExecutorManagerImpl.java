package thread;

import java.util.concurrent.ScheduledThreadPoolExecutor;

import thread.dao.ExecutorDao;
import thread.domain.Thread;

public class ExecutorManagerImpl implements ExecutorManager {

	private static ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(100);

	private ExecutorDao executorDao;

	@Override
	public void excute(Executor executor) {
		Thread xpThread = executorDao.selectExecutor(executor.getThreadName());
		if (null != xpThread) {
			return;
		}
		DefaultExecutor defaultXPExecutor = new DefaultExecutor(executor);
		pool.execute(defaultXPExecutor);
	}
	
	public static void main(String[] args) {
		System.out.println(-1 << (Integer.SIZE - 3));
	}

}
