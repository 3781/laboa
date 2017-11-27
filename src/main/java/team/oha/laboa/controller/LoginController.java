package team.oha.laboa.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.oha.laboa.service.UserService;
import team.oha.laboa.vo.ApiVo;
import team.oha.laboa.vo.LoginVO;


/**
 * <p>登录控制</p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/11/27
 * @modified
 */
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * <p>登录</p>
     *
     * @author loser
     * @version 1.0
     * @data 2017/11/27
     * @modified
     */
    @PostMapping("/login")
    public ApiVo login(LoginVO loginVO){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginVO.getUsername(), loginVO.getPassword());
        subject.login(token);

        userService.updateLoginTime(token.getUsername());

        ApiVo apiVo = new ApiVo();
        apiVo.setSuccess(true);
        apiVo.setMessage("登录成功....");

        return apiVo;
    }

    /**
     * <p>登出</p>
     *
     * @author loser
     * @version 1.0
     * @data 2017/11/27
     * @modified
     */
    @RequestMapping("/logout")
    public void logout(){
        SecurityUtils.getSubject().logout();
    }

    /**
     * <p>处理帐号不存在异常</p>
     *
     * @author loser
     * @version 1.0
     * @data 2017/11/27
     * @modified
     */
    @ExceptionHandler(UnknownAccountException.class)
    public ApiVo unknownAccountExceptionHandler() {
        ApiVo apiVo = new ApiVo();
        apiVo.setSuccess(false);
        apiVo.setMessage("帐号不存在");
        return apiVo;
    }

    /**
     * <p>处理密码错误异常</p>
     *
     * @author loser
     * @version 1.0
     * @data 2017/11/27
     * @modified
     */
    @ExceptionHandler(IncorrectCredentialsException.class)
    public ApiVo incorrectCredentialsExceptionHandler() {
        ApiVo apiVo = new ApiVo();
        apiVo.setSuccess(false);
        apiVo.setMessage("密码错误");
        return apiVo;
    }

    /**
     * <p>处理帐号锁定异常</p>
     *
     * @author loser
     * @version 1.0
     * @data 2017/11/27
     * @modified
     */
    @ExceptionHandler(LockedAccountException.class)
    public ApiVo lockedAccountExceptionHandler() {
        ApiVo apiVo = new ApiVo();
        apiVo.setSuccess(false);
        apiVo.setMessage("帐号申请未通过，请联系管理员");
        return apiVo;
    }

    /**
     * <p>处理帐号禁用异常</p>
     *
     * @author loser
     * @version 1.0
     * @data 2017/11/27
     * @modified
     */
    @ExceptionHandler(DisabledAccountException.class)
    public ApiVo disabledAccountExceptionHandler() {
        ApiVo apiVo = new ApiVo();
        apiVo.setSuccess(false);
        apiVo.setMessage("帐号被禁用，请联系管理员");
        return apiVo;
    }

    /**
     * <p>处理未知登录异常</p>
     *
     * @author loser
     * @version 1.0
     * @data 2017/11/27
     * @modified
     */
    @ExceptionHandler(AuthenticationException.class)
    public ApiVo authenticationExceptionHandler(AuthenticationException ae) {
        ApiVo apiVo = new ApiVo();
        apiVo.setSuccess(false);
        apiVo.setMessage("未知的登录错误，请联系管理员");
        logger.warn("登录过程中出现未知的登录异常", ae);
        return apiVo;
    }

    /**
     * <p>处理未知错误异常</p>
     *
     * @author loser
     * @version 1.0
     * @data 2017/11/27
     * @modified
     */
    @ExceptionHandler(Exception.class)
    public ApiVo exceptionHandler(Exception e) {
        ApiVo apiVo = new ApiVo();
        apiVo.setSuccess(false);
        apiVo.setMessage("未知错误，请联系管理员");
        logger.warn("登录控制中出现未知异常", e);
        return apiVo;
    }
}
