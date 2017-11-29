package team.oha.laboa.service.impl;

import org.apache.shiro.authz.AuthorizationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.oha.laboa.dao.UserDao;
import team.oha.laboa.dao.UserinfoDao;
import team.oha.laboa.dto.ApiDto;
import team.oha.laboa.dto.LoginDto;
import team.oha.laboa.model.UserDo;
import team.oha.laboa.model.UserinfoDo;
import team.oha.laboa.service.UserService;
import team.oha.laboa.shiro.model.UserAuthenticationInfo;
import team.oha.laboa.util.MD5Util;
import team.oha.laboa.vo.RegisterVo;

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
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername(username);
        loginDto.setLastLoginTime(userDao.getByUsername(username).getLoginTime());

        UserDo userDo = new UserDo();
        userDo.setUsername(username);
        userDo.setLoginTime(LocalDateTime.now());
        userDao.update(userDo);


        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        apiDto.setInfo(loginDto);
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
