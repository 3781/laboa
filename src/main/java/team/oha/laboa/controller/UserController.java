package team.oha.laboa.controller;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import team.oha.laboa.dto.ApiDto;
import team.oha.laboa.exception.UnknownUserException;
import team.oha.laboa.exception.WrongPasswordException;
import team.oha.laboa.service.UserService;
import team.oha.laboa.vo.PasswordChangeVo;
import team.oha.laboa.vo.UserinfoVo;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/12/1
 * @modified
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/info")
    public ApiDto getInfo(){
        return userService.getInfo((String)SecurityUtils.getSubject().getPrincipal());
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/info")
    public ApiDto updateInfo(@RequestBody UserinfoVo userinfoVo){
        userinfoVo.setUsername((String)SecurityUtils.getSubject().getPrincipal());
        return userService.updateInfo(userinfoVo);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/info/{username}")
    public ApiDto updateInfo(@PathVariable String username, @RequestBody UserinfoVo userinfoVo){
        userinfoVo.setUsername(username);
        return userService.updateInfo(userinfoVo);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/password")
    public ApiDto changePassword(@RequestBody PasswordChangeVo passwordChangeVo){
        passwordChangeVo.setUsername((String)SecurityUtils.getSubject().getPrincipal());
        return userService.changePassword(passwordChangeVo);
    }

    /**
     * <p>处理用户不存在异常</p>
     *
     * @author loser
     * @version 1.0
     * @data 2017/11/27
     * @modified
     */
    @ExceptionHandler(UnknownUserException.class)
    public ApiDto unknownUserExceptionHandler(UnknownUserException uue) {
        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(false);
        apiDto.setInfo(uue.getMessage());
        return apiDto;
    }

    /**
     * <p>处理密码不匹配异常</p>
     *
     * @author loser
     * @version 1.0
     * @data 2017/11/27
     * @modified
     */
    @ExceptionHandler(WrongPasswordException.class)
    public ApiDto wrongPasswordExceptionHandler(WrongPasswordException wpe) {
        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(false);
        apiDto.setInfo("密码不正确");
        logger.info("用户[{}]修改密码： [{}]", wpe.getOperator(), wpe.getMessage());
        return apiDto;
    }
}
