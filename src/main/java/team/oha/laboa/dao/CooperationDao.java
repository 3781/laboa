package team.oha.laboa.dao;

import team.oha.laboa.dto.CooperationDto;
import team.oha.laboa.dto.CooperationTreeDto;
import team.oha.laboa.model.CooperationDo;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/12/7
 * @modified
 */
public interface CooperationDao {
    Integer save(CooperationDo cooperationDo);
    Integer update(CooperationDo cooperationDo);
    Integer delete(Integer id);
    CooperationDto get(Integer id);
    CooperationTreeDto getTree(Integer id);
    CooperationTreeDto getTreeNode(Integer id);
}
