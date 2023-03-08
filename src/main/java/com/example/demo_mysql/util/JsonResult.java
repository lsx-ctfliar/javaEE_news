package com.example.demo_mysql.util;

public class JsonResult {



//    新建这个类  用来规范前台的信息传递，，，相当于是模板

    private Object obj;
    private String msg;
    private Integer code;

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {

        this.obj = obj;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
