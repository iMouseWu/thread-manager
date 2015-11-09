package configuration;

import configuration.domain.GroupPoolConfig;
import configuration.domain.PriorityPoolConfig;

import java.util.List;

/**
 * 线程配置
 *
 * @author wuhao
 */
public interface PoolConfiguration {

    /**
     * 获取优先级线程池配置
     *
     * @return
     */
    List<PriorityPoolConfig> getPriorityPoolConfig();

    /**
     * 获取任务组线程池配置
     *
     * @return
     */
    List<GroupPoolConfig> getGroupPoolConfig();

    /**
     * 根据线程优选级获取线程数量
     *
     * @param taskPriority 优先级
     * @return 线程数量
     */
    int getPriorityPoolCount(int taskPriority);

    /**
     * 根据任务组别获取线程数量
     *
     * @param groupId 组别
     * @return 线程数量
     */
    int getGroupPoolCount(int groupId);

    /**
     * 根据执行的次数,返回下次执行的时间
     *
     * @return
     */
    long getNextExecuteTime(int retryTime);


}
