package thread.domain;

/**
 * 线程实体接口
 *
 * @author wuhao
 */
public interface ThreadDO {

    /**
     * 线程实体,唯一标示一个线程
     */
    String getThreadId();

    /**
     * 线程名称
     */
    String getThreadName();

    /**
     * 线程提交的IP 地址
     */
    String getIp();

}
