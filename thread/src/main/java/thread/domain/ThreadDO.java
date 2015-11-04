package thread.domain;

/**
 * 线程实体接口
 *
 * @author wuhao
 */
public interface ThreadDO {

    /**
     * 线程实体,唯一标示一个线程
     *
     * @return
     */
    String getThreadId();

    /**
     * 线程名称
     *
     * @return
     */
    String getThreadName();

}
