package team.oha.laboa.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import team.oha.laboa.model.UserDo;

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
}
