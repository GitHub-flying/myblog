package com.ke.web.util;

/**
 * @author ke
 * @ClassName ResponseObject
 * @Description TOOD
 * @Date 2019/10/11
 * @Version 1.0
 **/
public class ResponseObject {
    private Integer code;
    private String msg;
    private Object data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * 构造方法私有，禁止外部类创建对象
     */
    public  ResponseObject() {

    }
    /**
     * 静态方法，对外提供该类的对象，请求成功无数据返回
     * @param code
     * @param msg
     * @return
     */
     public static ResponseObject success(Integer code, String msg) {
         ResponseObject ro = new ResponseObject();
         ro.setCode(code);
         ro.setMsg(msg);
         return ro;
     }

    /**
     * 静态方法，对外提供该类对象，请求成功有数据返回
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public static ResponseObject success(Integer code, String msg, Object data) {
        ResponseObject ro = new ResponseObject();
        ro.setCode(code);
        ro.setMsg(msg);
        ro.setData(data);
        return ro;

    }
    public static ResponseObject error(Integer code, String msg) {
        ResponseObject ro = new ResponseObject();
        ro.setCode(code);
        ro.setMsg(msg);
        return ro;
    }

}
