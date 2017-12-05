package team.oha.laboa.service;

import team.oha.laboa.dto.ApiDto;
import team.oha.laboa.vo.AgendaSummaryVo;
import team.oha.laboa.vo.AgendaVo;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/12/5
 * @modified
 */
public interface AgendaService {
    ApiDto savePersonalAgenda(AgendaVo agendaVo);
    ApiDto summaryAgenda(AgendaSummaryVo agendaSummaryVo);
}
