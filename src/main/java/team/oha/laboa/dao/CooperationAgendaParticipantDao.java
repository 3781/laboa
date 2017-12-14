package team.oha.laboa.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import team.oha.laboa.dto.ParticipantAvailableDto;
import team.oha.laboa.model.CooperationAgendaParticipantDo;
import team.oha.laboa.query.cooperation.participant.ParticipantAvailableQuery;
import team.oha.laboa.vo.AgendaVo;

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
    Integer saveBatch(AgendaVo agendaVo);
    Integer deleteBatch(AgendaVo agendaVo);
    List<ParticipantAvailableDto> listAvailable(ParticipantAvailableQuery memberAvailableQuery);
}
