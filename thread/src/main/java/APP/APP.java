package APP;

import configuration.DefaultPoolConfiguration;
import configuration.PoolConfiguration;
import thread.*;
import thread.dao.ExecutorTaskDao;
import thread.dao.ExecutorTaskDaoImpl;

/**
 * @author wuhao
 */
public class APP {

    public static void main(String[] args) {
        ExecutorTaskManager executorTaskManager = init();

        for (int i = 0; i < 100; i++) {
            DefaultExecutorTask defaultExecutorTask = new DefaultExecutorTask();
            defaultExecutorTask.setIp("127.0.0.1");
            defaultExecutorTask.setTaskId(i + "");
            defaultExecutorTask.setTaskName(i + "Thread name");
            executorTaskManager.excute(defaultExecutorTask);
        }

        for (int i = 0; i < 10; i++) {
            ExceptionExecutorTask defaultExecutorTask = new ExceptionExecutorTask();
            defaultExecutorTask.setIp("127.0.0.1");
            defaultExecutorTask.setTaskId(i + "");
            defaultExecutorTask.setTaskName(i + "Exception Thread name");
            executorTaskManager.excute(defaultExecutorTask);
        }
    }

    private static ExecutorTaskManager init() {
        ExecutorTaskManagerImpl executorTaskManager = new ExecutorTaskManagerImpl();

        ExceptionTaskResolve exceptionTaskResolve = new ExceptionTaskDelayResolve();
        executorTaskManager.setExceptionTaskResolve(exceptionTaskResolve);

        ExecutorTaskDao executorTaskDao = new ExecutorTaskDaoImpl();
        executorTaskManager.setExecutorDao(executorTaskDao);

        PoolConfiguration poolConfiguration = DefaultPoolConfiguration.getInstance();
        executorTaskManager.setPoolConfiguration(poolConfiguration);

        new Thread() {
            public void run() {
                exceptionTaskResolve.resolve();
            }

        }.start();
        return executorTaskManager;
    }


}
