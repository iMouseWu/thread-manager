package thread;

import thread.dao.ExecutorDao;
import thread.domain.DefaultThreadMeta;
import thread.domain.Thread;

public class DefaultExecutor implements Executor, Runnable {

	private Executor xpExecutor;

	private ExecutorDao executorDao;

	public DefaultExecutor() {

	}

	public DefaultExecutor(Executor xpExecutor) {
		this.xpExecutor = xpExecutor;
	}

	@Override
	public String getThreadName() {
		return xpExecutor.getThreadName();
	}

	@Override
	public void execute() {
		xpExecutor.execute();
	}

	@Override
	public void run() {
		Thread xpThread = executorDao.selectExecutor(getThreadId());
		initXPThread(xpThread);
		executorDao.insertExecutor(xpThread);
		this.execute();
	}

	private boolean initXPThread(Thread xpThread) {
		if (null != xpThread) {
			return true;
		}
		xpThread = new DefaultThreadMeta();
		xpThread.setThreadName(getThreadName());
		xpThread.setThreadId(getThreadId());
		return true;
	}

	@Override
	public String getThreadId() {
		return xpExecutor.getThreadId();
	}
}
