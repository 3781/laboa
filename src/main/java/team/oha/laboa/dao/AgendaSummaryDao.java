package team.oha.laboa.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import team.oha.laboa.dto.AgendaToDoDto;
import team.oha.laboa.model.AgendaSummaryDo;
import team.oha.laboa.query.agenda.AgendaToDoQuery;

import java.util.List;

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
public interface AgendaSummaryDao {
    Integer save(AgendaSummaryDo agendaSummaryDo);
    Integer update(AgendaSummaryDo agendaSummaryDo);
    List<AgendaToDoDto> listToDo(AgendaToDoQuery agendaToDoQuery);

    Integer generatePersonalSummary();
    Integer generateCooperationSummary();
    Integer cleanCooperationSummary();
}
