package team.oha.laboa.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import team.oha.laboa.shiro.model.UserAuthenticationInfo;
import team.oha.laboa.shiro.service.UserService;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/11/26
 * @modified
 */
public class UserRealm extends AuthorizingRealm {

    private UserService userService;

    public UserRealm(CredentialsMatcher matcher, UserService userService) {
        super(matcher);
        this.userService = userService;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return userService.getAuthorizationInfo(principals.getPrimaryPrincipal());
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UserAuthenticationInfo info = userService.getAuthenticationInfo(token.getPrincipal());

        if(info == null){
            throw new UnknownAccountException();
        }

        if(info.isLocked()){
            throw new LockedAccountException();
        }

        if(info.isDisable()){
            throw new DisabledAccountException();
        }

        return info;
    }
}
