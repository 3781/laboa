package team.oha.laboa.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import team.oha.laboa.dto.AgendaDto;
import team.oha.laboa.model.AgendaDo;
import team.oha.laboa.query.agenda.AgendaFilterQuery;
import team.oha.laboa.query.agenda.AgendaSelectQuery;
import team.oha.laboa.vo.AgendaBatchVo;

import java.time.LocalDateTime;
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
    Integer deleteBatch(AgendaBatchVo agendaBatchVo);
    Integer openBatch(AgendaBatchVo agendaBatchVo);
    Integer closeBatch(AgendaBatchVo agendaBatchVo);
    List<AgendaDo> listNeedGenerateItemAgenda();
    List<AgendaDo> listNeedReGenerateItemAgenda(@Param("nowTime") LocalDateTime nowTime);
    List<AgendaDto> list(AgendaSelectQuery agendaSelectQuery);
    Integer count(AgendaFilterQuery filterQuery);
    Integer update(AgendaDo agendaDo);
}
