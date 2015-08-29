package thread.dao;

import thread.domain.Thread;

public interface ExecutorDao {

	Thread selectExecutor(String threadId);

	void insertExecutor(Thread xpThread);

}
