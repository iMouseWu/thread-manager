package thread;

import configuration.PoolConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import thread.constants.TaskStrategy;
import thread.constants.ThreadStatus;
import thread.dao.ExecutorTaskDao;
import thread.domain.TaskProperty;
import thread.domain.ThreadDO;
import thread.domain.impl.DefaultThreadDO;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * 任务管理器,执行任务
 *
 * @author wuhao
 */
public class ExecutorTaskManagerImpl implements ExecutorTaskManager {

    private static Map<Integer, ScheduledThreadPoolExecutor> priorityPoolMap = new HashMap<>();

    private static Map<Integer, ScheduledThreadPoolExecutor> groupPoolMap = new HashMap<>();

    private PoolConfiguration poolConfiguration;

    private static final Integer DEFAULTCOUNT = 10;

    private ExecutorTaskDao executorDao;

    private ExceptionTaskResolve exceptionTaskResolve;

    private Logger logger = LoggerFactory.getLogger(ExecutorTaskManagerImpl.class);

    @Override
    public void excute(final ExecutorTask executorTask) {
        if (!needContinueAndInit(executorTask)) {
            return;
        }
        TaskProperty taskProperty = executorTask.getTaskProperty();
        if (null == taskProperty) {
            taskProperty = new TaskProperty() {
                @Override
                public TaskStrategy getTaskStrategy() {
                    return TaskStrategy.taskPriority;
                }

                @Override
                public int getTaskPriority() {
                    return -1;
                }

                @Override
                public int getTaskGroup() {
                    return -1;
                }
            };
        }
        ScheduledThreadPoolExecutor pool = gainPool(taskProperty);

        Work work = new Work(executorTask);
        pool.execute(work);
    }

    private boolean needContinueAndInit(ExecutorTask executorTask) {
        boolean needContinue = false;
        ThreadDO threadDO = executorDao.selectExecutorTask(executorTask.getTaskId(), executorTask.getIp());
        if (null == threadDO) {
            needContinue = executorDao.insertExecutorTask(createThreadDO(executorTask));
            return needContinue;
        }
        if (threadDO.getThreadStatus() == ThreadStatus.EXCEPTION) {
            needContinue = executorDao.updateExcutorTaskStatusAndRetryTime(threadDO.getTaskId(), threadDO.getIp(), ThreadStatus.EXCEPTION, ThreadStatus.INIT);
        }
        return needContinue;
    }

    private DefaultThreadDO createThreadDO(ExecutorTask executorTask) {
        DefaultThreadDO defaultThreadDO = new DefaultThreadDO(executorTask);
        defaultThreadDO.setThreadStatus(ThreadStatus.INIT);
        return defaultThreadDO;
    }

    private ScheduledThreadPoolExecutor gainPool(TaskProperty taskProperty) {
        ScheduledThreadPoolExecutor pool = null;
        TaskStrategy taskStrategy = taskProperty.getTaskStrategy();
        if (taskStrategy == TaskStrategy.taskPriority) {
            int taskPriority = taskProperty.getTaskPriority();
            pool = priorityPoolMap.get(taskPriority);
            if (null == pool) {
                int count = poolConfiguration.getPriorityPoolCount(taskPriority);
                if (count == 0) {
                    count = DEFAULTCOUNT;
                }
                pool = new ScheduledThreadPoolExecutor(count);
                priorityPoolMap.put(taskPriority, pool);
            }
        } else if (taskStrategy == TaskStrategy.taskGroup) {
            int taskGroup = taskProperty.getTaskGroup();
            pool = groupPoolMap.get(taskGroup);
            if (null == pool) {
                int count = poolConfiguration.getGroupPoolCount(taskGroup);
                if (count == 0) {
                    count = DEFAULTCOUNT;
                }
                pool = new ScheduledThreadPoolExecutor(count);
                groupPoolMap.put(taskGroup, pool);
            }
        } else {
            throw new RuntimeException("cannot find the taskStrategy is " + taskStrategy);
        }
        return pool;
    }

    private class Work implements Runnable {

        private ExecutorTask executorTask;

        public Work(ExecutorTask executorTask) {
            this.executorTask = executorTask;
        }

        @Override
        public void run() {
            String taskId = executorTask.getTaskId();
            String ip = executorTask.getIp();
            try {
                executorDao.updateExcutorTaskStatus(taskId, ip, ThreadStatus.INIT, ThreadStatus.DOING);
                executorTask.execute();
                executorDao.updateExcutorTaskStatus(taskId, ip, ThreadStatus.DOING, ThreadStatus.DELETE);
            } catch (Throwable e) {
                ThreadDO threadDO = executorDao.selectExecutorTask(taskId, ip);
                doAfterException(threadDO);
                logger.error("", e);
            } finally {
//                callBack(executorTask);
            }
        }

        private void doAfterException(ThreadDO threadDO) {
            int retryTime = threadDO.getRetryTime();
            String taskId = threadDO.getTaskId();
            String ip = executorTask.getIp();
            long nextExecuteTime = poolConfiguration.getNextExecuteTime(retryTime);
            if (nextExecuteTime == 0L) {
                executorDao.updateExcutorTaskStatus(taskId, ip, ThreadStatus.DOING, ThreadStatus.FAILED);
                return;
            }
            executorDao.updateExcutorTaskStatusAndExecuteTime(taskId, ip, ThreadStatus.DOING, ThreadStatus.EXCEPTION, nextExecuteTime);
        }
    }
}
