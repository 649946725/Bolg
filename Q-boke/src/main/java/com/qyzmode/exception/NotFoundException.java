package com.qyzmode.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
自定义一些异常 方便统一处理
 */
@ResponseStatus(HttpStatus.NOT_FOUND)//NOT_FOUND-404 会转换成资源找不到的状态码 以达到我们想要的404跳转
//@ResponseStatus的作用就是为了改变HTTP响应的状态码
public class NotFoundException extends RuntimeException {
    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
