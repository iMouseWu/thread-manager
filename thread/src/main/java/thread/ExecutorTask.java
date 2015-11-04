package thread;

import thread.domain.TaskProperty;

/**
 * 任务对象
 *
 * @author wuhao
 */
public interface ExecutorTask {

    /**
     * 获取任务ID(需要保证唯一性)
     */
    String getTaskId();

    /**
     * 获取任务提交的IP地址
     * @return
     */
    String getIp();

    /**
     * 获取任务名称
     *
     * @return
     */
    String getTaskName();

    /**
     * 标示线程要执行的任务
     */
    void execute();

    /**
     * 获取任务属性
     *
     * @return
     */
    TaskProperty getTaskProperty();

}
