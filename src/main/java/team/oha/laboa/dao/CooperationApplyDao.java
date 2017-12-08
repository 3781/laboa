package team.oha.laboa.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import team.oha.laboa.dto.CooperationApplyDto;
import team.oha.laboa.model.CooperationApplyDo;
import team.oha.laboa.query.cooperation.apply.ApplyFilterQuery;
import team.oha.laboa.query.cooperation.apply.ApplySelectQuery;
import team.oha.laboa.vo.ApplyDealBatchVo;

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
public interface CooperationApplyDao {
    Integer save(CooperationApplyDo cooperationApplyDo);
    Integer dealApply(ApplyDealBatchVo applyDealBatchVo);

    List<CooperationApplyDto> list(ApplySelectQuery applySelectQuery);
    Integer count(ApplyFilterQuery filterQuery);
}
