package team.oha.laboa.service.impl;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.oha.laboa.dao.UserDao;
import team.oha.laboa.dao.UserinfoDao;
import team.oha.laboa.dto.*;
import team.oha.laboa.exception.UnknownUserException;
import team.oha.laboa.model.UserDo;
import team.oha.laboa.model.UserinfoDo;
import team.oha.laboa.query.user.UserSelectQuery;
import team.oha.laboa.service.PasswordService;
import team.oha.laboa.service.UserService;
import team.oha.laboa.shiro.model.UserAuthenticationInfo;
import team.oha.laboa.vo.*;

import java.time.LocalDateTime;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/11/26
 * @modified
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserinfoDao userinfoDao;
    @Autowired
    private PasswordService passwordService;

    @Override
    public ApiDto register(RegisterVo registerVo) {
        UserDo userDo = new UserDo();
        userDo.setUsername(registerVo.getUsername());
        userDo.setPassword(registerVo.getPassword());
        userDo.setRegisterTime(LocalDateTime.now());
        userDo.setRole(UserDo.Role.enduser);
        userDo.setStatus(UserDo.Status.locked);
        passwordService.encryptPassword(userDo);
        userDao.save(userDo);

        UserinfoDo userinfoDo = new UserinfoDo();
        userinfoDo.setUserId(userDo.getUserId());
        userinfoDo.setQq(registerVo.getQq());
        userinfoDo.setPhone(registerVo.getPhone());
        userinfoDo.setName(registerVo.getName());
        userinfoDo.setEmployeeNumber(registerVo.getEmployeeNumber());
        userinfoDo.setEmail(registerVo.getEmail());
        userinfoDao.save(userinfoDo);

        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        return apiDto;
    }

    @Override
    public ApiDto login(String username) {
        UserDo userDo = userDao.getByUsername(username);

        LoginDto loginDto = new LoginDto();
        loginDto.setUsername(username);
        loginDto.setRole(userDo.getRole());
        loginDto.setPermissions(userDao.listCooperationRole(username));
        loginDto.setLastLoginTime(userDo.getLoginTime());

        UserDo updateUserDo = new UserDo();
        updateUserDo.setUsername(username);
        updateUserDo.setLoginTime(LocalDateTime.now());
        userDao.update(updateUserDo);

        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        apiDto.setInfo(loginDto);
        return apiDto;
    }

    @Override
    public ApiDto getLoginInfo(String username) {
        UserDo userDo = userDao.getByUsername(username);

        LoginDto loginDto = new LoginDto();
        loginDto.setUsername(username);
        loginDto.setRole(userDo.getRole());
        loginDto.setPermissions(userDao.listCooperationRole(username));
        loginDto.setLastLoginTime(userDo.getLoginTime());
        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        apiDto.setInfo(loginDto);
        return apiDto;
    }

    @Override
    public ApiDto changePassword(PasswordChangeVo passwordChangeVo) {
        UserDo userDo = userDao.getByUsername(passwordChangeVo.getUsername());
        if(userDo==null){
            throw new UnknownUserException(passwordChangeVo.getUsername());
        }

        passwordService.checkPassword(userDo, passwordChangeVo.getOldPassword());
        userDo.setPassword(passwordChangeVo.getNewPassword());
        passwordService.encryptPassword(userDo);

        ApiDto apiDto = new ApiDto();
        if( userDao.updatePassword(userDo) == 1){
            apiDto.setSuccess(true);
            apiDto.setInfo("新密码设置成功");
        }else{
            apiDto.setSuccess(false);
            apiDto.setInfo("密码设置失败");
        }

        return apiDto;
    }

    @Override
    public ApiDto getInfo(String username) {
        UserDo userDo = userDao.getByUsername(username);
        ApiDto apiDto = new ApiDto();

        if(userDo==null){
            throw new UnknownUserException(username);
        }
        apiDto.setSuccess(true);
        UserinfoDto userinfoDto = new UserinfoDto();
        UserinfoDo userinfoDo =  userinfoDao.getByUserId(userDo.getUserId());
        BeanUtils.copyProperties(userinfoDo, userinfoDto);
        apiDto.setInfo(userinfoDto);
        return apiDto;
    }

    @Override
    public ApiDto updateInfo(UserinfoVo userinfoVo) {
        UserDo userDo = userDao.getByUsername(userinfoVo.getUsername());
        ApiDto apiDto = new ApiDto();

        if(userDo==null){
            throw new UnknownUserException(userinfoVo.getUsername());
        }

        UserinfoDo userinfoDo = new UserinfoDo();
        BeanUtils.copyProperties(userinfoVo, userinfoDo);
        userinfoDo.setUserId(userDo.getUserId());

        if( userinfoDao.update(userinfoDo) == 1){
            apiDto.setSuccess(true);
            apiDto.setInfo("用户信息更新成功");
        }else{
            apiDto.setSuccess(false);
            apiDto.setInfo("更新失败");
        }

        return apiDto;
    }

    @Transactional(readOnly = true)
    @Override
    public AuthorizationInfo getAuthorizationInfo(Object principal) {
        UserDo userDo = userDao.getByUsername((String)principal);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRole(userDo.getRole().name());
        authorizationInfo.addStringPermissions(userDao.listCooperationRole((String)principal));
        return authorizationInfo;
    }

    @Override
    @Transactional(readOnly = true)
    public UserAuthenticationInfo getAuthenticationInfo(Object principal) {
        UserDo userDo = userDao.getByUsername((String)principal);

        if(userDo==null){
            return null;
        }

        UserAuthenticationInfo info = new UserAuthenticationInfo();
        info.setPrincipal(userDo.getUsername());
        info.setCredentials(userDo.getPassword());
        info.setCredentialsSalt(userDo.getSalt());
        info.setLocked(UserDo.Status.locked.equals(userDo.getStatus()));
        info.setDisable(UserDo.Status.disable.equals(userDo.getStatus()));
        return info;
    }

    @Override
    @Transactional(readOnly = true)
    public ApiDto listUsers(UserSelectQuery userSelectQuery) {
        PageDto<UserDto> pageDto = new PageDto<>();
        pageDto.setTotalSize(userDao.count(userSelectQuery.getFilterQuery()));
        if(pageDto.getTotalSize() != 0){
            pageDto.setData(userDao.list(userSelectQuery));
        }

        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        apiDto.setInfo(pageDto);
        return apiDto;
    }

    @Override
    public ApiDto configureStatus(ConfigureStatusVo configureStatusVo) {
        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        apiDto.setInfo(userDao.configureStatus(configureStatusVo));
        return apiDto;
    }

    @Override
    public ApiDto configureRole(ConfigureRoleVo configureRoleVo) {
        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        apiDto.setInfo(userDao.configureRole(configureRoleVo));
        return apiDto;
    }

    @Value("${defaultPassword}")
    private String DEFAULT_PASSWORD;

    @Override
    public ApiDto resetPassword(ResetPasswordVo resetPasswordVo) {
        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        resetPasswordVo.setPassword(DEFAULT_PASSWORD);
        passwordService.encryptPassword(resetPasswordVo);
        apiDto.setInfo(userDao.resetPassword(resetPasswordVo));
        return apiDto;
    }
}
