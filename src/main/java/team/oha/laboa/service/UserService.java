package team.oha.laboa.service;

import team.oha.laboa.dto.ApiDto;
import team.oha.laboa.query.user.UserSelectQuery;
import team.oha.laboa.vo.*;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/11/27
 * @modified
 */
public interface UserService extends team.oha.laboa.shiro.service.UserService{
    ApiDto register(RegisterVo registerVo);
    ApiDto login(String username);
    ApiDto getLoginInfo(String username);
    ApiDto changePassword(PasswordChangeVo passwordChangeVo);
    ApiDto getInfo(String username);
    ApiDto updateInfo(UserinfoVo userinfoVo);
    ApiDto listUsers(UserSelectQuery userSelectQuery);
    ApiDto configureStatus(ConfigureStatusVo configureStatusVo);
    ApiDto configureRole(ConfigureRoleVo configureRoleVo);
    ApiDto resetPassword(ResetPasswordVo resetPasswordVo);
}
