package team.oha.laboa.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.oha.laboa.dto.ApiDto;
import team.oha.laboa.service.UserService;
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
    public ApiDto login(@RequestBody LoginVO loginVO){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginVO.getUsername(), loginVO.getPassword());
        subject.login(token);

        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        apiDto.setInfo(userService.getLoginSuccessInfo(token.getUsername()));

        userService.updateLoginTime(token.getUsername());

        return apiDto;
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
    public ApiDto unknownAccountExceptionHandler() {
        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(false);
        apiDto.setInfo("帐号不存在");
        return apiDto;
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
    public ApiDto incorrectCredentialsExceptionHandler() {
        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(false);
        apiDto.setInfo("密码错误");
        return apiDto;
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
    public ApiDto lockedAccountExceptionHandler() {
        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(false);
        apiDto.setInfo("帐号申请未通过，请联系管理员");
        return apiDto;
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
    public ApiDto disabledAccountExceptionHandler() {
        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(false);
        apiDto.setInfo("帐号被禁用，请联系管理员");
        return apiDto;
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
    public ApiDto authenticationExceptionHandler(AuthenticationException ae) {
        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(false);
        apiDto.setInfo("未知的登录错误，请联系管理员");
        logger.warn("登录过程中出现未知的登录异常", ae);
        return apiDto;
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
    public ApiDto exceptionHandler(Exception e) {
        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(false);
        apiDto.setInfo("未知错误，请联系管理员");
        logger.warn("登录控制中出现未知异常", e);
        return apiDto;
    }
}
