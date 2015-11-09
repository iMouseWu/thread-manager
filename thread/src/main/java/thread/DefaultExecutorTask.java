package thread;

import thread.domain.TaskProperty;

public class DefaultExecutorTask implements ExecutorTask {

    private String taskId;

    private String ip;

    private String taskName;

    private TaskProperty taskProperty;

    @Override
    public String getTaskId() {
        return taskId;
    }

    @Override
    public String getIp() {
        return ip;
    }

    @Override
    public String getTaskName() {
        return taskName;
    }

    @Override
    public void execute() {
        System.out.println(taskName + " execute");
    }

    @Override
    public TaskProperty getTaskProperty() {
        return taskProperty;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskProperty(TaskProperty taskProperty) {
        this.taskProperty = taskProperty;
    }
}
