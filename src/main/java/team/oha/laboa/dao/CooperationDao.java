package team.oha.laboa.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import team.oha.laboa.dto.CooperationDto;
import team.oha.laboa.dto.CooperationTreeDto;
import team.oha.laboa.model.CooperationDo;
import team.oha.laboa.query.cooperation.CooperationFilterQuery;
import team.oha.laboa.query.cooperation.CooperationSelectQuery;

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
public interface CooperationDao {
    Integer save(CooperationDo cooperationDo);
    Integer update(CooperationDo cooperationDo);
    Integer delete(Integer id);
    CooperationDto get(Integer id);
    CooperationTreeDto getTree(Integer id);
    CooperationTreeDto getTreeNode(Integer id);
    List<CooperationDto> list(CooperationSelectQuery cooperationSelectQuery);
    Integer count(CooperationFilterQuery filterQuery);
}
