package team.oha.laboa.shiro.service;

import org.apache.shiro.authz.AuthorizationInfo;
import team.oha.laboa.shiro.model.UserAuthenticationInfo;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/11/26
 * @modified
 */
public interface UserService {
    AuthorizationInfo getAuthorizationInfo(Object principal);
    UserAuthenticationInfo getAuthenticationInfo(Object principal);
}
