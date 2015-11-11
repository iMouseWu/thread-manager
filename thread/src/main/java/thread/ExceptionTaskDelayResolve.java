package thread;

import java.util.concurrent.DelayQueue;

/**
 * @author wuhao
 */
public class ExceptionTaskDelayResolve implements ExceptionTaskResolve {

	private static DelayQueue<ExceptionRetryTask> delayQueue = new DelayQueue<>();

	private ExecutorTaskManager executorTaskManager;

	public void setExecutorTaskManager(ExecutorTaskManager executorTaskManager) {
		this.executorTaskManager = executorTaskManager;
	}

	@Override
	public void resolve() {
		while (!Thread.interrupted()) {
			ExceptionRetryTask exceptionRetryTask = null;
			try {
				exceptionRetryTask = delayQueue.take();
				executorTaskManager.excute(exceptionRetryTask);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}

		}
	}

	@Override
	public void add(ExceptionRetryTask exceptionRetryTask) {
		delayQueue.offer(exceptionRetryTask);
	}
}
