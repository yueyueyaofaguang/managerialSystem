package com.fridy.backend.base.result;

public enum ResponseCode {
    SUCCESS(200,"请求成功"),
    TABLE_SUCCESS(0,"请求成功"),
    FAIL(500,"请求失败"),
    PARAMETER_MISSING(600,"参数缺失"),
    UNAUTHORIZED(401,"未授权"),
    EXITPHONE(50000,"电话号码已经存在"),
    EXITEMAIL(50001,"邮箱已经存在"),
    EXITUSERNAME(50002,"用户名已经存在");
    private Integer code;
    private String message;

    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String getMessage(String name){
        for(ResponseCode item:ResponseCode.values()){
            if(item.name().equals(name)){
                return item.message;
            }
        }
        return null;
    }

    public static Integer getCode(String name){
        for(ResponseCode item:ResponseCode.values()){
            if(item.name().equals(name)){
                return item.code;
            }
        }
        return null;
    }
}
