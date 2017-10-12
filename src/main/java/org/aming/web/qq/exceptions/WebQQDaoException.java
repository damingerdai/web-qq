package org.aming.web.qq.exceptions;

/**
 * @arthur aming
 * @version 2017-10-12
 * Dao层异常
 */
public class WebQQDaoException  extends RuntimeException {

    private static final long serialVersionUID = -6938498189136673933L;

    private String errorSql;
    private Throwable cause;

    public String getMessage() {
        return "fail to execute sql : " + this.errorSql;
    }

    @Override
    public Throwable getCause() {
        return cause;
    }

    public WebQQDaoException(String errorSql) {
        super();
        this.errorSql = errorSql;
    }

    public WebQQDaoException(String errorSql, Throwable cause) {
        super();
        this.errorSql = errorSql;
        this.cause = cause;
    }
}
