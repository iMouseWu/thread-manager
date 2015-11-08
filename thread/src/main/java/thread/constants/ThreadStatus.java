package thread.constants;

/**
 * @author wuhao
 */
public enum ThreadStatus {
    INIT(0, "初始化"), DOING(1, "执行中"), DELETE(2, "执行完成"),EXCEPTION(3,"执行异常"),FAILED(4,"执行失败");

    private int code;

    private String desc;

    private ThreadStatus(int code, String desc) {

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
