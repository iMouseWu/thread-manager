package configuration;

import configuration.domain.GroupPoolConfig;
import configuration.domain.PriorityPoolConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuhao
 */
public class DefaultPoolConfiguration implements PoolConfiguration {

    private static DefaultPoolConfiguration poolConfiguration = new DefaultPoolConfiguration();

    private List<PriorityPoolConfig> priorityPoolConfigs;

    private List<GroupPoolConfig> groupPoolConfigs;


    static {
        List<PriorityPoolConfig> priorityPoolConfigs = new ArrayList<>();
        PriorityPoolConfig priorityPoolConfig = new PriorityPoolConfig();
        priorityPoolConfig.setTaskPriority(1);
        priorityPoolConfig.setThreadCount(100);
        priorityPoolConfigs.add(priorityPoolConfig);

        List<GroupPoolConfig> groupPoolConfigs = new ArrayList<>();
        GroupPoolConfig groupPoolConfig = new GroupPoolConfig();
        groupPoolConfig.setGroupId(1);
        groupPoolConfig.setThreadCount(100);
        groupPoolConfigs.add(groupPoolConfig);

        poolConfiguration.setGroupPoolConfigs(groupPoolConfigs);
        poolConfiguration.setPriorityPoolConfigs(priorityPoolConfigs);
    }


    private DefaultPoolConfiguration() {

    }

    public static PoolConfiguration getInstance() {
        return poolConfiguration;
    }

    @Override
    public List<PriorityPoolConfig> getPriorityPoolConfig() {
        return priorityPoolConfigs;
    }

    @Override
    public List<GroupPoolConfig> getGroupPoolConfig() {
        return groupPoolConfigs;
    }

    public void setPriorityPoolConfigs(List<PriorityPoolConfig> priorityPoolConfigs) {
        this.priorityPoolConfigs = priorityPoolConfigs;
    }

    public void setGroupPoolConfigs(List<GroupPoolConfig> groupPoolConfigs) {
        this.groupPoolConfigs = groupPoolConfigs;
    }

    @Override
    public int getPriorityPoolCount(int taskPriority) {
        for (PriorityPoolConfig priorityPoolConfig : priorityPoolConfigs) {
            if (priorityPoolConfig.getTaskPriority() == taskPriority) {
                return priorityPoolConfig.getThreadCount();
            }
        }
        return 0;
    }

    @Override
    public int getGroupPoolCount(int groupId) {
        for (GroupPoolConfig groupPoolConfig : groupPoolConfigs) {
            if (groupPoolConfig.getGroupId() == groupId) {
                return groupPoolConfig.getThreadCount();
            }
        }
        return 0;
    }

    @Override
    public long getNextExecuteTime(int retryTime) {
        long currentTime = System.currentTimeMillis();
        if (retryTime <= 4) {
            return currentTime + retryTime * 1L * 60 * 1000;
        }
        return 0;
    }
}
