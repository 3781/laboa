package team.oha.laboa.controller;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>处理全部登录异常和权限异常</p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/11/26
 * @modified
 */
@RestControllerAdvice
public class ControllerExceptionHandler {

    /**
     * <p>未登录返回401</p>
     *
     * @author loser
     * @version 1.0
     * @data 2017/11/26
     * @modified
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({ UnauthenticatedException.class, AuthenticationException.class })
    public void authenticationExceptionHandler() {
    }

    /**
     * <p>无权访问返回403</p>
     *
     * @author loser
     * @version 1.0
     * @data 2017/11/26
     * @modified
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({ UnauthorizedException.class, AuthorizationException.class })
    public void authorizationExceptionHandler() {
    }
}
