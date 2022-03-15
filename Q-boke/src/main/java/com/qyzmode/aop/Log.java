package com.qyzmode.aop;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//日志记录实体类 1 url 2 ip 3 url调用的方法 4 参数  5 返回结果
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Log {

    private String url;
    private String ip;
    private String classMethod;
    private Object [] args;
}
