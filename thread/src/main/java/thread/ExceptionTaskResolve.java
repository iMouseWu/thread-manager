package thread;

/**
 * @author wuhao
 */
public interface ExceptionTaskResolve {

    void resolve();

    void add(ExceptionRetryTask exceptionRetryTask);


}
