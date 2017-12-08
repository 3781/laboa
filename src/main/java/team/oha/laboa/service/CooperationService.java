package team.oha.laboa.service;

import team.oha.laboa.dto.ApiDto;
import team.oha.laboa.query.cooperation.CooperationSelectQuery;
import team.oha.laboa.query.cooperation.apply.ApplySelectQuery;
import team.oha.laboa.query.cooperation.member.MemberAvailableQuery;
import team.oha.laboa.query.cooperation.member.MemberSelectQuery;
import team.oha.laboa.vo.CooperationVo;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/12/8
 * @modified
 */
public interface CooperationService {

    ApiDto saveCooperation(CooperationVo cooperationVo);
    ApiDto listAvailable(MemberAvailableQuery memberAvailableQuery);
    ApiDto listCooperation(CooperationSelectQuery cooperationSelectQuery);
    ApiDto listApply(ApplySelectQuery applySelectQuery);
    ApiDto listMember(MemberSelectQuery memberSelectQuery);
}
