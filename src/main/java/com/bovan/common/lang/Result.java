package com.bovan.common.lang;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private Integer code;
    private T data;
    private String msg;


    //统一接口
    //构造函数
//    public Result(Integer code,String msg,T data){
//        this.code = code;
//        this.msg = msg;
//        this.data = data;
//    }

    public static <T> Result success(T data) {
        return success(200, "操作成功", data);
    }

    public static <T> Result success(Integer code, String msg, T data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static <T> Result fail(String msg) {
        return fail(400, msg, null);
    }

    public static <T> Result fail(String msg, T data) {
        return fail(400, msg, data);
    }

    public static <T> Result fail(Integer code, String msg, T data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
