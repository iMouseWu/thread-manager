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

    int getPriorityPoolCount(int taskPriority);

    int getGroupPoolCount(int groupId);



}
