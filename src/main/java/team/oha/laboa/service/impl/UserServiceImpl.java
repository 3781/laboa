package team.oha.laboa.service.impl;

import org.apache.shiro.authz.AuthorizationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.oha.laboa.dao.UserDao;
import team.oha.laboa.dao.UserinfoDao;
import team.oha.laboa.dto.ApiDto;
import team.oha.laboa.dto.LoginDto;
import team.oha.laboa.dto.UserinfoDto;
import team.oha.laboa.exception.UnknownUserException;
import team.oha.laboa.exception.WrongPasswordException;
import team.oha.laboa.model.UserDo;
import team.oha.laboa.model.UserinfoDo;
import team.oha.laboa.service.UserService;
import team.oha.laboa.shiro.model.UserAuthenticationInfo;
import team.oha.laboa.util.MD5Util;
import team.oha.laboa.vo.PasswordChangeVo;
import team.oha.laboa.vo.RegisterVo;
import team.oha.laboa.vo.UserinfoVo;

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

    @Override
    public ApiDto register(RegisterVo registerVo) {
        UserDo userDo = new UserDo();
        userDo.setUsername(registerVo.getUsername());
        userDo.setSalt(MD5Util.generateSalt().toHex());
        userDo.setPassword(MD5Util.encryptPassword(registerVo.getPassword(), userDo.getSalt()));
        userDo.setRegisterTime(LocalDateTime.now());
        userDo.setRole(UserDo.Role.enduser);
        userDo.setStatus(UserDo.Status.locked);
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

        if(!userDo.getPassword().equals(
                MD5Util.encryptPassword(passwordChangeVo.getOldPassword(), userDo.getSalt()))){
            throw new WrongPasswordException(userDo.getUsername(), passwordChangeVo.getOldPassword());
        }

        userDo.setSalt(MD5Util.generateSalt().toHex());
        userDo.setPassword(MD5Util.encryptPassword(passwordChangeVo.getNewPassword(), userDo.getSalt()));

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
        return null;
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

}
