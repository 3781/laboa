package team.oha.laboa.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import team.oha.laboa.dto.AgendaDto;
import team.oha.laboa.model.AgendaDo;
import team.oha.laboa.query.agenda.AgendaFilterQuery;
import team.oha.laboa.query.agenda.AgendaSelectQuery;
import team.oha.laboa.vo.BatchVo;

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
public interface AgendaDao {
    Integer save(AgendaDo agendaDo);
    List<AgendaDto> list(AgendaSelectQuery agendaSelectQuery);
    Integer count(AgendaFilterQuery filterQuery);
    Integer close(BatchVo batchVo);
    Integer update(AgendaDo agendaDo);
}
