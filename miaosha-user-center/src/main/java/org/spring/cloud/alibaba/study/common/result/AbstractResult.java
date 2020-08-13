package org.spring.cloud.alibaba.study.common.result;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yzw
 * @Classname ResultMessage
 * @Description TODO
 * @Date 2020/8/13 11:17
 */
@Data
@NoArgsConstructor
public class AbstractResult{

    private StatusEnum statusEnum;
    private int code;
    private String msg;

    protected AbstractResult(StatusEnum statusEnum, int code, String msg){
        this.code = code;
        this.statusEnum = statusEnum;
        this.msg = msg;
    }

    public AbstractResult(StatusEnum statusEnum) {
        this.statusEnum = statusEnum;
        this.code = statusEnum.getCode();
        this.msg = statusEnum.getMsg();
    }

    public static boolean isSuccess(AbstractResult result){
        return result != null && result.statusEnum == StatusEnum.SUCCESS;
    }

    public static boolean isRegisterSuccess(AbstractResult result){
        return result != null && result.statusEnum == StatusEnum.REGISTER_SUCCESS;
    }


}
