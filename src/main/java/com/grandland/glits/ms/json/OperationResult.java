package com.grandland.glits.ms.json;

/**
 * OperationResult 存放后台执行结果
 * Created by lwz on 2016/1/22.
 */
public class OperationResult<T> {


    private boolean hasError;

    private String message;

    private T bean;

    public boolean isHasError() {
        return hasError;
    }

    public OperationResult() {
        super();
    }

    public OperationResult(boolean hasError, String message) {
        this.hasError = hasError;
        this.message = message;
    }

    public OperationResult(boolean hasError, String message, T bean){
        this(hasError,message);
        this.bean = bean;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBean() {
        return bean;
    }

    public void setBean(T bean) {
        this.bean = bean;
    }

    @Override
    public String toString() {
        return "OpreateResult [hasError=" + hasError + ", message=" + message
                + "]";
    }




}
