package com.grandland.glits.ms.json;

import java.util.List;

/**
 * OperationResult 存放后台执行结果
 * Created by lwz on 2016/1/22.
 */
public class OperationResult {


    private boolean hasError;

    private String message;

    private List<String> list;

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

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "OpreateResult [hasError=" + hasError + ", message=" + message
                + "]";
    }




}
