package com.fridy.backend.base.result;

import com.fridy.backend.model.SysUser;
import lombok.Data;

import javax.xml.transform.Result;
import java.io.Serializable;
import java.util.List;

@Data
public class Results<T> implements Serializable {
    int count;//数据量
    Integer code;//代码
    String msg;
    List<T> datas;
    T data;

    public Results() {
    }


    public Results(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Results(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Results(int count, Integer code, String msg, List<T> datas) {
        this.count = count;
        this.code = code;
        this.msg = msg;
        this.datas = datas;
    }

    /**无数据传输的 成功返回*/
    public static Results success(){
        return new Results(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMessage());
    }

    public static <T>Results<T> success(String msg){
        return new Results<T>(ResponseCode.SUCCESS.getCode(),msg);
    }

    public static<T>Results<T> success(ResponseCode responseCode){
        return new Results<T>(responseCode.getCode(),responseCode.getMessage());
    }
    /**有数据返回**/
    public static <T>Results<T> succcess(T data){
        return new Results<T>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), data);
    }

    public static<T>Results<T> fail(){
        return new Results(ResponseCode.FAIL.getCode(),ResponseCode.FAIL.getMessage());
    }
    /**返回分页数据**/
    public static <T>Results<T> succcess(Integer count, List<T> data) {
        return new Results<T>(count,0,ResponseCode.SUCCESS.getMessage(),data);
    }

    public static Results fail(ResponseCode responseCode) {
        return new Results(responseCode.getCode(),responseCode.getMessage());
    }
}
