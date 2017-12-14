package team.oha.laboa.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import team.oha.laboa.model.CooperationAgendaDo;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/12/7
 * @modified
 */
@Repository
@Mapper
public interface CooperationAgendaDao {
    Integer save(CooperationAgendaDo cooperationAgendaDo);
    Integer generateSummary();
    Integer cleanSummary();
}
