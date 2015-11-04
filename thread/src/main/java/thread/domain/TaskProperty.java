package thread.domain;

import thread.constants.TaskStrategy;

/**
 * 任务属性类
 *
 * @author wuhao
 */
public interface TaskProperty {
    /**
     * 获取任务优先级别
     *
     * @return
     */
    int getTaskPriority();

    /**
     * 获取任务组
     *
     * @return
     */
    int getTaskGroup();

    /**
     * 获取任务策略(0表示用优先级来安排任务,1表示用任务组的方式安排策略)
     *
     * @return
     */
    TaskStrategy getTaskStrategy();

}
