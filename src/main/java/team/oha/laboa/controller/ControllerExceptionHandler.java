package team.oha.laboa.controller;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import team.oha.laboa.dto.ApiDto;

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

    private Logger logger = LoggerFactory.getLogger(getClass());

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

    /**
     * <p>处理未知错误异常</p>
     *
     * @author loser
     * @version 1.0
     * @data 2017/11/27
     * @modified
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApiDto exceptionHandler(Exception e) {
        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(false);
        apiDto.setInfo("出现未知错误，请联系管理员");
        logger.error("未知异常", e);
        return apiDto;
    }
}
