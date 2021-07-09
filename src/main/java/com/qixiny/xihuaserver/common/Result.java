package com.qixiny.xihuaserver.common;

import com.sun.istack.internal.Nullable;
import lombok.Data;

@Data
public class Result {
    private int code;
    private String msg;
    private Object data;

    public Result(int code, String msg,Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Result success(String msg,Object data){ return new Result(200,msg,data); }
    public static Result success(String msg){
        return new Result(200,msg);
    }

    public static Result ServerError(String msg, Object data){
        return new Result(500,msg,data);
    }
    public static Result ServerError(String msg){
        return new Result(500,msg);
    }

    public static Result ClientError(String msg,Object data){
        return new Result(400,msg,data);
    }
    public static Result ClientError(String msg){
        return new Result(400,msg);
    }
}
