package team.oha.laboa.dao;

import team.oha.laboa.model.CooperationMemberDo;
import team.oha.laboa.vo.BatchVo;

import java.util.List;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/12/7
 * @modified
 */
public interface CooperationMemberDao {
    Integer save(CooperationMemberDo cooperationMemberDo);
    Integer saveBatch(List<CooperationMemberDo> cooperationMemberDos);
    Integer deleteBatch(BatchVo batchVo);
}
