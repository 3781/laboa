package team.oha.laboa.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import team.oha.laboa.dto.CooperationMemberDto;
import team.oha.laboa.dto.MemberAvailableDto;
import team.oha.laboa.model.CooperationMemberDo;
import team.oha.laboa.query.cooperation.member.MemberAvailableQuery;
import team.oha.laboa.query.cooperation.member.MemberFilterQuery;
import team.oha.laboa.query.cooperation.member.MemberSelectQuery;
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
@Repository
@Mapper
public interface CooperationMemberDao {
    Integer save(CooperationMemberDo cooperationMemberDo);
    Integer saveBatch(List<CooperationMemberDo> cooperationMemberDos);
    Integer deleteBatch(BatchVo batchVo);
    List<MemberAvailableDto> listAvailable(MemberAvailableQuery memberAvailableQuery);
    List<CooperationMemberDto> list(MemberSelectQuery memberSelectQuery);
    Integer count(MemberFilterQuery filterQuery);
    
}
