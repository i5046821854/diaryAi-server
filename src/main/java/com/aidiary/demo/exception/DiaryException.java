package com.aidiary.demo.exception;

public class DiaryException extends RuntimeException{

    public DiaryException() {
        super("DiaryException Occurs");
    }

    public DiaryException(String msg) {
        super(msg);
    }
}
