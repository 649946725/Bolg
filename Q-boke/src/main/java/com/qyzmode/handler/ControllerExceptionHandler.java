package com.qyzmode.handler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/*
   用来处理异常界面--指定跳转到自定义的视图  对所有的异常进行处理  全局异常处理
   @ControllerAdvice注解
   这是一个增强的 Controller。使用这个 Controller ，可以实现三个方面的功能：
    全局异常处理
    全局数据绑定
    全局数据预处理
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    /*
    @ExceptionHandler 注解用来指明异常的处理类型，
    即如果这里指定为 NullpointerException，则数组越界异常就不会进到这个方法中来。
     */
    //使用指定类初始化日志对象 在日志输出的时候，可以打印出日志信息所在的类，如下：
    // Logger logger = LoggerFactory.getLogger(com.User.class); logger.debug("用户信息"); 将会打印出: com.User : 用户信息
    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)//参数是可以检测到异常的标注级别
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        //记录日常信息
        logger.error("Request URL:{},Exception:{}",request.getRequestURL(),e);
        //通过注释工具类AnnotationUtils的找到注释findAnnotation(该异常类，状态码类)返回该异常类的状态码
        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null)
            throw e;
        ModelAndView modelAndView=new ModelAndView();
        //返回发生异常请求的URL
        modelAndView.addObject(request.getRequestURL());
        //返回发生的异常
        modelAndView.addObject(e);
        //自定义发生异常时返回的视图
        modelAndView.setViewName("error/error");
        return modelAndView;
    }
}
