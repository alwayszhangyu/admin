package com.autumn.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 罗泽宏+马欣雨
 * @version 1.0
 * @createDate  2023/6/2 17:19
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code;
    private String  message;
    private T data;

    public static <T> Result<T> success(){
        return new Result<T>(20000,"success",null);
    }

    public static <T> Result<T> success(String msg){
        return new Result<T>(20000,msg,null);
    }

    public static <T> Result<T> success(T data){
        return new Result<T>(20000,"success",data);
    }

    public static <T> Result<T> success(String msg, T data){
        return new Result<T>(20000,msg,data);
    }

    public static <T> Result<T> fail(){
        return new Result<T>(300,"fail",null);
    }

    public static <T> Result<T> fail(String msg){
        return new Result<T>(300,msg,null);
    }
    public static <T> Result<T> fail(T data){
        return new Result<T>(300,"fail",data);
    }
    public static <T> Result<T> fail(String msg, T data){
        return new Result<T>(300,msg,data);
    }
}
