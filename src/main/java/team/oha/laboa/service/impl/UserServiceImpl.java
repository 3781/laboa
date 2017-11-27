package team.oha.laboa.service.impl;

import org.apache.shiro.authz.AuthorizationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.oha.laboa.dao.UserDao;
import team.oha.laboa.model.UserDo;
import team.oha.laboa.service.UserService;
import team.oha.laboa.shiro.model.UserAuthenticationInfo;
import team.oha.laboa.util.MD5Util;

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

    @Override
    public void saveUser(UserDo userDo) {
        userDo.setSalt(MD5Util.generateSalt().toHex());
        userDo.setPassword(MD5Util.encryptPassword(userDo.getPassword(), userDo.getSalt()));
        userDao.save(userDo);
    }

    @Override
    public void updateLoginTime(String username) {
        UserDo userDo = new UserDo();
        userDo.setUsername(username);
        userDo.setLoginTime(LocalDateTime.now());
        userDao.update(userDo);
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
