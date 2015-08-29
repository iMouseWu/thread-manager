package thread.domain;

/**
 * 秀品线程实体接口
 * 
 * @author wuhao
 *
 */
public interface Thread {

	/**
	 * 线程实体
	 * 
	 * @return
	 */
	String getThreadId();

	void setThreadId(String threadId);

	/**
	 * 线程名称
	 * 
	 * @return
	 */
	String getThreadName();

	void setThreadName(String threadName);

}
