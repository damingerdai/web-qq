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
    private Throwable cause;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }

    public WebQQException(){
        super();
        this.code = DEFAULT_CODE;
        this.message = DEFAULT_MESSAGE;
    }

    public WebQQException(Throwable cause){
        super();
        this.code = DEFAULT_CODE;
        this.message = DEFAULT_MESSAGE;
        this.cause = cause;
    }

    public WebQQException(int code, String message, Throwable cause) {
        super();
        this.code = code;
        this.message = message;
        this.cause = cause;
    }

}
