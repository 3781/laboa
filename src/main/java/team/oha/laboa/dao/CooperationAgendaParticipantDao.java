package team.oha.laboa.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import team.oha.laboa.model.CooperationAgendaParticipantDo;

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
public interface CooperationAgendaParticipantDao {
    Integer save(CooperationAgendaParticipantDo cooperationAgendaParticipantDo);
    Integer saveBatch(@Param("cooperationAgendaId") Integer cooperationAgendaId, @Param("memberIds") Integer[] memberIds);
    Integer delete(Integer id);
}
