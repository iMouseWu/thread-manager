package thread;

/**
 * 秀品业务对象
 * 
 * @author wuhao
 *
 */
public interface Executor {

	/**
	 * 获取线程的ID,需要外部使用者保证唯一性
	 */
	String getThreadId();

	/**
	 * 获取线程名称
	 * 
	 * @return
	 */
	String getThreadName();

	/**
	 * 标示线程要执行的任务
	 */
	void execute();

}
