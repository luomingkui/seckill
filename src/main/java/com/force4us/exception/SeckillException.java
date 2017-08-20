package com.force4us.exception;

/**
 * Created by chengjinqian on 2017/4/18.
 */
public class SeckillException extends RuntimeException{
    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
