package org.aming.web.qq.response;

import org.aming.web.qq.exceptions.WebQQException;

import java.io.Serializable;

/**
 * @author daming
 * @version 2017/10/3.
 */
public class CommonResponse implements Serializable {

    private static final long serialVersionUID = 7980325592134636957L;

    private static final boolean DEFAULT_SUCCESS_FLAG = true;

    private boolean succcess;
    private Object data;
    private WebQQException error;

    public static CommonResponse getCommonResponse(boolean succcess,Object data){
        return new CommonResponse(succcess,data);
    }

    public static CommonResponse getSuccessCommonResponse(Object data){
        return getCommonResponse(true,data);
    }

    public static CommonResponse getErrorCommonResponse(Throwable error){
        if(error instanceof WebQQException){
            return new CommonResponse(false,(WebQQException)error);
        } else {
            return new CommonResponse(false,new WebQQException(new Throwable(error.getMessage())));
        }
    }

    public static CommonResponse getErrorCommonResponse(int code, String message, Throwable cause){
        WebQQException error = new WebQQException(code,message,cause);
        return new CommonResponse(false,error);
    }

    public boolean isSucccess() {
        return succcess;
    }

    public void setSucccess(boolean succcess) {
        this.succcess = succcess;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public WebQQException getError() {
        return error;
    }

    public void setError(WebQQException error){
        this.error = error;
    }

    public CommonResponse() {
        super();
        this.succcess = DEFAULT_SUCCESS_FLAG;
    }

    public CommonResponse(boolean succcess, Object data) {
        super();
        this.succcess = succcess;
        this.data = data;
    }

    public CommonResponse(boolean succcess,WebQQException error){
        super();
        this.succcess = succcess;
        this.error = error;
    }
}
