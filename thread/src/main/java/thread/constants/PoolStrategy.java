package thread.constants;

/**
 * @author wuhao
 */
public enum PoolStrategy {

    WITHIP(0, "以ip策略过滤线程,不同机器IP线程Id可以相同"), WITHOUTIP(1, "不以IP过滤线程,不同线程Id不能重复");

    private int code;

    private String desc;

    PoolStrategy(int code, String desc) {
        this.code = code;
        this.desc = desc;
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
