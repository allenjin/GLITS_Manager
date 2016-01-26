package com.grandland.glits.ms.exception;

/**
 * Created by lwz
 * on 2016/1/26.
 */
public class AlreadyExistException extends Exception{

    public AlreadyExistException(){
    }

    public AlreadyExistException(String message){
        super(message);
    }

    public AlreadyExistException(Throwable cause) {
        super(cause);
    }

    public AlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
