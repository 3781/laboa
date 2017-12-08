package team.oha.laboa.dao;

import team.oha.laboa.model.CooperationApplyDo;
import team.oha.laboa.vo.ApplyDealBatchVo;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/12/7
 * @modified
 */
public interface CooperationApplyDao {
    Integer save(CooperationApplyDo cooperationApplyDo);
    Integer dealApply(ApplyDealBatchVo applyDealBatchVo);
}
