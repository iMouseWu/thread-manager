package thread.domain;

/**
 * 
 * @author wuhao
 *
 */
public class DefaultThreadMeta implements Thread {

	private String threadName;

	private String threadId;

	@Override
	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	@Override
	public String getThreadId() {
		return threadId;
	}

	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}

}
