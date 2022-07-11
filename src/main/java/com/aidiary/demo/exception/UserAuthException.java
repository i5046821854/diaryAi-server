package com.aidiary.demo.exception;

public class UserAuthException extends RuntimeException{

    public UserAuthException() {
        super("UserAuthException Occurs");
    }

    public UserAuthException(String msg) {
        super(msg);
    }
}
