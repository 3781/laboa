package team.oha.laboa.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import team.oha.laboa.dto.ApiDto;
import team.oha.laboa.exception.UnknownUserException;
import team.oha.laboa.exception.WrongPasswordException;
import team.oha.laboa.model.UserDo;
import team.oha.laboa.query.user.UserSelectQuery;
import team.oha.laboa.service.UserService;
import team.oha.laboa.vo.*;

import java.util.ArrayList;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/12/1
 * @modified
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    /**
     * <p>分页用户列表</p>
     *
     * @author loser
     * @version 1.0
     * @data 2017/11/27
     * @modified
     */
    @RequiresRoles(value = {"admin","superAdmin"}, logical = Logical.OR)
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/list")
    public ApiDto list(UserSelectQuery userSelectQuery){
        return userService.listUsers(userSelectQuery);
    }

    /**
     * <p>获得登录信息</p>
     *
     * @author loser
     * @version 1.0
     * @data 2017/11/27
     * @modified
     */
    @RequiresUser
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/loginInfo")
    public ApiDto loginInfo(){
        return userService.getLoginInfo((String)SecurityUtils.getSubject().getPrincipal());
    }

    /**
     * <p>获得个人信息</p>
     *
     * @author loser
     * @version 1.0
     * @data 2017/11/27
     * @modified
     */
    @RequiresUser
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/info")
    public ApiDto getInfo(){
        return userService.getInfo((String)SecurityUtils.getSubject().getPrincipal());
    }

    /**
     * <p>更新个人信息</p>
     *
     * @author loser
     * @version 1.0
     * @data 2017/11/27
     * @modified
     */
    @RequiresUser
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/info")
    public ApiDto updateInfo(@RequestBody UserinfoVo userinfoVo){
        userinfoVo.setUsername((String)SecurityUtils.getSubject().getPrincipal());
        return userService.updateInfo(userinfoVo);
    }

    /**
     * <p>更改密码</p>
     *
     * @author loser
     * @version 1.0
     * @data 2017/11/27
     * @modified
     */
    @RequiresUser
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/password")
    public ApiDto changePassword(@RequestBody PasswordChangeVo passwordChangeVo){
        passwordChangeVo.setUsername((String)SecurityUtils.getSubject().getPrincipal());
        return userService.changePassword(passwordChangeVo);
    }

    /**
     * <p>更改状态</p>
     *
     * @author loser
     * @version 1.0
     * @data 2017/11/27
     * @modified
     */
    @RequiresRoles(value = {"admin","superAdmin"}, logical = Logical.OR)
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/status")
    public ApiDto configureStatus(@RequestBody ConfigureStatusVo configureStatusVo){
        if(SecurityUtils.getSubject().hasRole("superAdmin")){
            configureStatusVo.setAllowRoles(new UserDo.Role[]{UserDo.Role.enduser, UserDo.Role.admin});
        }else {
            configureStatusVo.setAllowRoles(new UserDo.Role[]{UserDo.Role.enduser});
        }

        return userService.configureStatus(configureStatusVo);
    }

    /**
     * <p>更改角色</p>
     *
     * @author loser
     * @version 1.0
     * @data 2017/11/27
     * @modified
     */
    @RequiresRoles(value = {"superAdmin"})
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/role")
    public ApiDto configureRole(@RequestBody ConfigureRoleVo configureRoleVo){
        return userService.configureRole(configureRoleVo);
    }

    /**
     * <p>重置密码</p>
     *
     * @author loser
     * @version 1.0
     * @data 2017/11/27
     * @modified
     */
    @RequiresRoles(value = {"admin","superAdmin"}, logical = Logical.OR)
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/passwordReset")
    public ApiDto passwordReset(@RequestBody ResetPasswordVo resetPasswordVo){
        if(SecurityUtils.getSubject().hasRole("superAdmin")){
            resetPasswordVo.setAllowRoles(new ArrayList<UserDo.Role>(){{ add(UserDo.Role.enduser);add(UserDo.Role.admin);}});
        }else {
            resetPasswordVo.setAllowRoles(new ArrayList<UserDo.Role>(){{ add(UserDo.Role.enduser);}});
        }
        return userService.resetPassword(resetPasswordVo);
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
