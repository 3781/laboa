package team.oha.laboa.service;

import team.oha.laboa.dto.ApiDto;
import team.oha.laboa.dto.LoginDto;
import team.oha.laboa.vo.RegisterVo;

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
}
