package team.oha.laboa.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import team.oha.laboa.model.UserinfoDo;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/11/28
 * @modified
 */
@Repository
@Mapper
public interface UserinfoDao {
    int save(UserinfoDo userinfoDo);
    UserinfoDo getByUserId(Integer userId);
    int update(UserinfoDo userinfoDo);
}
