package org.spring.cloud.alibaba.study.common.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yzw
 * @Classname ResultMessage
 * @Description TODO
 * @Date 2020/8/13 11:27
 */
@Data
public class ResultMessage<T> extends AbstractResult implements Serializable {

    private static final long serialVersionUID = 867933019328199779L;

    private T data;
    private Integer count;

    private volatile static ResultMessage successResult;

    protected ResultMessage(){
        super();
    }

    protected ResultMessage(StatusEnum status,  int code, String msg){
        super(status, code, msg);
    }

    public ResultMessage(StatusEnum statusEnum) {
        super(statusEnum);
    }


    static enum ResultMessageEnum{
        INSTANCE;

        private ResultMessage resultMessage;

        private ResultMessageEnum(){
            resultMessage = new ResultMessage(StatusEnum.SUCCESS);
        }

        public <T> ResultMessage<T> getInstance(){
            return resultMessage;
        }
    }


    public static <T> ResultMessage<T> getInstance(){
        return ResultMessageEnum.INSTANCE.getInstance();
    }



}
