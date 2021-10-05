package com.fzu.bbs.utils;

import lombok.Data;

import java.io.Serializable;

@Data // lombok
public class R<T> implements Serializable {
    private static final long serialVersionUID = 6738387175874422264L;
    private Integer code;
    private String message;
    private T data;

    private R() {
    }

    public static <T> R<T> ok() {
        return createResult(ResponseCode.SUCCESS.getCode(), null, null);
    }

    public static <T> R<T> ok(T data) {
        return createResult(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), data);
    }

    public static <T> R<T> ok(String message) {
        return createResult(ResponseCode.SUCCESS.getCode(), message, null);
    }

    public static <T> R<T> ok(T data, String message) {
        return createResult(ResponseCode.SUCCESS.getCode(), message, data);
    }

    public static <T> R<T> fail() {
        return createResult(ResponseCode.FAIL.getCode(), ResponseCode.FAIL.getMessage(), null);
    }

    public static <T> R<T> fail(ResponseCode responseCode) {
        return createResult(responseCode.getCode(), responseCode.getMessage(), null);
    }

    public static <T> R<T> fail(ResponseCode responseCode, T data) {
        return createResult(responseCode.getCode(), responseCode.getMessage(), data);
    }

    public static <T> R<T> fail(ResponseCode responseCode, String message) {
        return createResult(responseCode.getCode(), String.format("%s %s", responseCode.getMessage(), message), null);
    }

    public static <T> R<T> fail(Integer code, String message) {
        return createResult(code, message, null);
    }

    public static <T> R<T> fail(String messege){
        return createResult(ResponseCode.FAIL.getCode(), messege,null);
    }

    private static <T> R<T> createResult(Integer code, String message, T data) {
        R<T> r = new R<>();
        r.setCode(code);
        r.setMessage(message);
        r.setData(data);
        return r;
    }
}