package com.driver;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String str){
        super(str);
    }
}
