package com.xgf.domain.msg;

//信息提示
public class Msg {

    private int code;   //返回编码（200正常、404未找到之类的）
    private String message; //返回提示信息
    private Object data;    //返回携带的数据

    //static静态方法，以后直接通过Msg.init()方法使用就行，简化
    public static Msg init(int code, String message, Object data){
        Msg result =new Msg(code,message,data);
        return result;
    }

    public Msg() {
    }

    public Msg(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
