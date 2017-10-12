package org.aming.web.qq.exceptions;

/**
 * @author daming
 * @version 2017/10/3.
 */
public class WebQQException extends RuntimeException {

    private static final long serialVersionUID = 7398282580857507024L;

    private static final int DEFAULT_CODE = 5000;
    private static final String DEFAULT_MESSAGE = "系统内部错误";

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public WebQQException setCode(int code) {
        this.code = code;
        return this;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public WebQQException setMessage(String message) {
        this.message = message;
        return this;
    }


    public WebQQException(){
        super();
        this.code = DEFAULT_CODE;
        this.message = DEFAULT_MESSAGE;
    }

    public WebQQException(Throwable cause){
        super();
        this.code = DEFAULT_CODE;
        this.message = cause.getMessage();
    }

    public WebQQException(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

}
