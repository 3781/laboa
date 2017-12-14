package team.oha.laboa.service;

import team.oha.laboa.dto.ApiDto;
import team.oha.laboa.query.cooperation.CooperationSelectQuery;
import team.oha.laboa.query.cooperation.apply.ApplySelectQuery;
import team.oha.laboa.query.cooperation.member.MemberAvailableQuery;
import team.oha.laboa.query.cooperation.member.MemberSelectQuery;
import team.oha.laboa.query.cooperation.participant.ParticipantAvailableQuery;
import team.oha.laboa.vo.ApplyDealBatchVo;
import team.oha.laboa.vo.CooperationMemberVo;
import team.oha.laboa.vo.CooperationVo;
import team.oha.laboa.vo.MemberRoleChangeVo;

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
    ApiDto updateCooperation(CooperationVo cooperationVo);
    ApiDto deleteCooperation(Integer cooperationId);
    ApiDto getCooperationById(Integer id);
    ApiDto listMemberAvailable(MemberAvailableQuery memberAvailableQuery);
    ApiDto listParticipantAvailable(ParticipantAvailableQuery participantAvailableQuery);
    ApiDto listCooperation(CooperationSelectQuery cooperationSelectQuery);
    ApiDto listCooperationTree(Integer cooperationId);
    ApiDto doApply(Integer cooperationId);
    ApiDto dealApply(ApplyDealBatchVo applyDealBatchVo);
    ApiDto listApply(ApplySelectQuery applySelectQuery);
    ApiDto saveMember(CooperationMemberVo cooperationMemberVo);
    ApiDto changeMemberRole(MemberRoleChangeVo memberRoleChangeVo);
    ApiDto deleteMember(Integer memberId);
    ApiDto listMember(MemberSelectQuery memberSelectQuery);
}
