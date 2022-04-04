package com.atguigu.servicebase.exceptionhandler;

import com.atguigu.commonutils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author Miracle Luna
 * @Date 2022/4/4 10:57
 * @Version 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)  //所有异常都执行
    @ResponseBody //为了返回数据
    public R error(Exception e){
        e.printStackTrace();
        return  R.error().message("执行了全局异常处理。。。");
    }
}
