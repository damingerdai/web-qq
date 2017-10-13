package org.aming.web.qq.response;

import org.aming.web.qq.exceptions.WebQQException;
import org.aming.web.qq.exceptions.WebQQServiceException;

import java.io.Serializable;

/**
 * @author daming
 * @version 2017/10/3.
 */
public class CommonResponse implements Serializable {

    private static final long serialVersionUID = 7980325592134636957L;

    private static final boolean DEFAULT_SUCCESS_FLAG = true;

    private boolean success;
    private Object data;
    private WebQQException error;

    @Deprecated
    public static CommonResponse getCommonResponse(boolean success,Object data){
        return new CommonResponse(success,data);
    }

    public static CommonResponse getSuccessCommonResponse(Object data){
        return new CommonResponse(true,data);
    }

    public static WebQQException getErrorCommonResponse(Throwable error) {
        if(error instanceof WebQQException){
            return (WebQQException)error;
        } else if (error instanceof WebQQServiceException) {
            return new WebQQException(5001,error.getMessage());
        } else {
            return new WebQQException();
        }
    }

    public static WebQQException getErrorCommonResponse(int code, String message) {
         return new WebQQException(code,message);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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
        this.success = DEFAULT_SUCCESS_FLAG;
    }

    public CommonResponse(boolean success, Object data) {
        super();
        this.success = success;
        this.data = data;
    }

    public CommonResponse(boolean success,WebQQException error){
        super();
        this.success = success;
        this.error = error;
    }
}
