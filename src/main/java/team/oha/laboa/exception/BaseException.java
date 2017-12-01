package team.oha.laboa.exception;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;


/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/12/1
 * @modified
 */
public class BaseException extends RuntimeException {

    final public String getOperator() {
        Subject subject = SecurityUtils.getSubject();
        return subject.isRemembered()||subject.isAuthenticated() ?
                (String)subject.getPrincipal() : "系统";
    }

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
