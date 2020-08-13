package org.spring.cloud.alibaba.study.common.result;

/**
 * @author yzw
 * @Classname StatusEnum
 * @Description TODO
 * @Date 2020/8/13 11:17
 */
public enum StatusEnum {

    SUCCESS(0, "操作成功"),
    FAILED(-1, "操作失败"),
    EXCEPTION(-1,"系统异常"),
    REGISTER_SUCCESS(1000, "注册成功"),
    REGISTER_FAIL(1001, "注册失败"),
    CODE_FAIL(1003, "验证失败");


    private int code;
    private String msg;

    private StatusEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
