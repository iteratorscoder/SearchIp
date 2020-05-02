package com.example.demo.entity;

import lombok.Data;

/**
 * 包含：是否获取成功+IP的信息
 */
@Data
public class Result {
    private String code;
    private String message;
    private IpZone ipZone;

    public Result() {
    }

    public Result(String code, String message, IpZone ipZone) {
        this.code = code;
        this.message = message;
        this.ipZone = ipZone;
    }

    public static Result success(IpZone ipZone){
        return new Result("200","success",ipZone);
    }

    public static Result fail(){
        return new Result("401","fail",new IpZone());
    }




}