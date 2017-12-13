package team.oha.laboa.service;

import team.oha.laboa.dto.ApiDto;
import team.oha.laboa.query.agenda.AgendaSelectQuery;
import team.oha.laboa.query.agenda.AgendaToDoQuery;
import team.oha.laboa.vo.AgendaBatchVo;
import team.oha.laboa.vo.AgendaSummaryVo;
import team.oha.laboa.vo.AgendaVo;
import team.oha.laboa.vo.CooperationAgendaParticipantVo;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/12/5
 * @modified
 */
public interface AgendaService {
    ApiDto saveAgenda(AgendaVo agendaVo);
    ApiDto deleteAgendas(AgendaBatchVo agendaBatchVo);
    ApiDto closeAgendas(AgendaBatchVo agendaBatchVo);
    ApiDto openAgendas(AgendaBatchVo agendaBatchVo);
    ApiDto summaryAgenda(AgendaSummaryVo agendaSummaryVo);
    ApiDto listOwnAgendas(AgendaSelectQuery agendaSelectQuery);
    ApiDto listJoinAgendas(AgendaSelectQuery agendaSelectQuery);
    ApiDto listToDo(AgendaToDoQuery agendaToDoQuery);
    ApiDto update(AgendaVo agendaVo);

    ApiDto saveCooperationAgendaParticipant(CooperationAgendaParticipantVo cooperationAgendaParticipantVo);
    ApiDto deleteCooperationAgendaParticipant(Integer participantId);

    ApiDto getAgendaDetail(Integer agendaId);

    void refreshAgenda();
}
