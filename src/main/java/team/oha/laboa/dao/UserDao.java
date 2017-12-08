package team.oha.laboa.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import team.oha.laboa.dto.UserDto;
import team.oha.laboa.model.UserDo;
import team.oha.laboa.query.user.UserFilterQuery;
import team.oha.laboa.query.user.UserSelectQuery;
import team.oha.laboa.vo.ConfigureRoleVo;
import team.oha.laboa.vo.ConfigureStatusVo;
import team.oha.laboa.vo.ResetPasswordVo;

import java.util.List;
import java.util.Set;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/11/27
 * @modified
 */
@Repository
@Mapper
public interface UserDao {
    UserDo getByUsername(String username);
    Integer update(UserDo user);
    Integer updatePassword(UserDo user);
    Integer save(UserDo userDo);
    List<UserDto> list(UserSelectQuery userSelectQuery);
    Integer count(UserFilterQuery  filterQuery);
    Integer configureStatus(ConfigureStatusVo configureStatusVo);
    Integer configureRole(ConfigureRoleVo configureRoleVo);
    Integer resetPassword(ResetPasswordVo resetPasswordVo);
    Set<String> listCooperationRole(String username);
}
