package com.qyzmode.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/*
日志记录 1 url 2 ip 3 url调用的方法 4 参数  5 返回结果
这里采用AOP面向切面编程来实现日志的记录  可以省去很多代码
 */
@Aspect//把当前类当成一个切面共容器读取
@Component //组件扫描器 为了方便注入
public class LogAop {

    //日志记录器
    private  final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Pointcut("execution(* com.qyzmode.controller.*.*(..))")//@Pointcut声明这是个切面 execution这个是用来指定来拦截那些类
    //第一个参数代表的是返回类型，访问修饰符号public这些可以省略
    public void log(){//切入点

    }
    //在切入点前做一些东西 记录请求的url ip 方法 传过来的参数
    @Before("log()")//传入切入点
    public void doBefore(JoinPoint joinPoint){
        //获取HttpRequest来获取url ip
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        String url=request.getRequestURI();
        String ip=request.getRemoteAddr();
        //通过这个切入点对象joinPoint来获取当前切入的类和方法和参数
        String classMethod=joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        Object[] args=joinPoint.getArgs();
        Log log=new Log(url,ip,classMethod,args);
        logger.info("Log:{}",log);
    }
    //在切入点后做一些东西
    @After("log()")//传入切入点
    public void doAfter(){
        logger.info("---------执行完毕了------------");
    }

    //在切入点的方法执行完后拦截  第一个方法执行完返回的结果
    @AfterReturning(returning = "result",pointcut = "log()")//传入切入点
    public void doAfterReturning(Object result){
        logger.info("Result："+result);
    }
}
