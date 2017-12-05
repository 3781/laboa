package team.oha.laboa.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import team.oha.laboa.model.AgendaDo;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/12/5
 * @modified
 */
@Repository
@Mapper
public interface AgendaDao {
    Integer save(AgendaDo agendaDo);
}
