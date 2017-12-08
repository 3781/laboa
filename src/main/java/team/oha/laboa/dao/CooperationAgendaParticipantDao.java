package team.oha.laboa.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import team.oha.laboa.model.CooperationAgendaParticipantDo;

import java.util.List;

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
    Integer saveBatch(List<CooperationAgendaParticipantDo> cooperationAgendaParticipantDos);
    Integer delete(Integer id);
}
