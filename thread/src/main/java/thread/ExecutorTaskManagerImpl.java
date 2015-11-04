package thread;

import configuration.PoolConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import thread.constants.PoolStrategy;
import thread.constants.TaskStrategy;
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

    private Map<Integer, ScheduledThreadPoolExecutor> priorityPoolMap = new HashMap<>();

    private Map<Integer, ScheduledThreadPoolExecutor> groupPoolMap = new HashMap<>();

    private PoolConfiguration poolConfiguration;

    private static final Integer DEFAULTCOUNT = 10;

    private ExecutorTaskDao executorDao;

    private Logger logger = LoggerFactory.getLogger(ExecutorTaskManagerImpl.class);

    @Override
    public void excute(final ExecutorTask executorTask) {
        PoolStrategy poolStrategy = poolConfiguration.getPoolStrategy();

        ThreadDO threadDO = null;
        if (poolStrategy == PoolStrategy.WITHIP) {
            threadDO = executorDao.selectExecutorTask(executorTask.getTaskId(), executorTask.getIp());
        } else if (poolStrategy == PoolStrategy.WITHOUTIP) {
            threadDO = executorDao.selectExecutorTask(executorTask.getTaskId());
        } else {
            throw new RuntimeException("poolStrategy cannot find " + poolStrategy);
        }
        if (null != threadDO) {
            logger.info("taskId is {},Ip is {} has commit repeat", executorTask.getTaskId(), executorTask.getIp());
            return;
        }
        executorDao.insertExecutorTask(createThreadDO(executorTask));

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

    private DefaultThreadDO createThreadDO(ExecutorTask executorTask) {
        DefaultThreadDO defaultThreadDO = new DefaultThreadDO();
        defaultThreadDO.setThreadId(executorTask.getTaskId());
        defaultThreadDO.setThreadName(executorTask.getTaskName());
        defaultThreadDO.setIp(executorTask.getIp());
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
            try {
                executorTask.execute();
            } catch (Throwable e) {
                logger.error("", e);
            } finally {
                callBack(executorTask);
            }
        }
    }

    private void callBack(ExecutorTask executorTask) {
        PoolStrategy poolStrategy = poolConfiguration.getPoolStrategy();
        if (poolStrategy == PoolStrategy.WITHIP) {
            executorDao.deleteExcutorTask(executorTask.getTaskId(), executorTask.getIp());
        } else if (poolStrategy == PoolStrategy.WITHOUTIP) {
            executorDao.deleteExcutorTask(executorTask.getTaskId());
        } else {
            throw new RuntimeException("poolStrategy cannot find " + poolStrategy);
        }

    }

}
