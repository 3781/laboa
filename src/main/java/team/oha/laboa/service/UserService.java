package team.oha.laboa.service;

import team.oha.laboa.model.UserDo;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/11/27
 * @modified
 */
public interface UserService extends team.oha.laboa.shiro.service.UserService{
    void saveUser(UserDo userDo);
    void updateLoginTime(String username);
}
