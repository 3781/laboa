package team.oha.laboa.dao;

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
public interface CooperationAgendaParticipantDao {
    Integer save(CooperationAgendaParticipantDo cooperationAgendaParticipantDo);
    Integer saveBatch(List<CooperationAgendaParticipantDo> cooperationAgendaParticipantDos);
}
