package org.aming.web.qq.exceptions;

/**
 * @author aming
 * @version 2017-10-12
 * Service层异常
 */
public class WebQQServiceException extends RuntimeException{

    private static final long serialVersionUID = -7873850467298337213L;

    private String message;
    private WebQQDaoException cause;

    @Override
    public String getMessage() {
        return message;
    }

    public WebQQServiceException setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public WebQQDaoException getCause() {
        return cause;
    }

    public WebQQServiceException setCause(WebQQDaoException cause) {
        this.cause = cause;
        return this;
    }

    public WebQQServiceException() {
        super();
    }

    public WebQQServiceException(String message, WebQQDaoException cause) {
        super();
        this.message = message;
        this.cause = cause;
    }
}
