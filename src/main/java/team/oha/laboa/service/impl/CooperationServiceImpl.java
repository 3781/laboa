package team.oha.laboa.service.impl;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.oha.laboa.dao.CooperationApplyDao;
import team.oha.laboa.dao.CooperationDao;
import team.oha.laboa.dao.CooperationMemberDao;
import team.oha.laboa.dao.UserDao;
import team.oha.laboa.dto.*;
import team.oha.laboa.model.CooperationApplyDo;
import team.oha.laboa.model.CooperationDo;
import team.oha.laboa.model.CooperationMemberDo;
import team.oha.laboa.query.cooperation.CooperationSelectQuery;
import team.oha.laboa.query.cooperation.apply.ApplySelectQuery;
import team.oha.laboa.query.cooperation.member.MemberAvailableQuery;
import team.oha.laboa.query.cooperation.member.MemberSelectQuery;
import team.oha.laboa.service.CooperationService;
import team.oha.laboa.vo.ApplyDealBatchVo;
import team.oha.laboa.vo.CooperationMemberVo;
import team.oha.laboa.vo.CooperationVo;
import team.oha.laboa.vo.MemberRoleChangeVo;

import java.time.LocalDateTime;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/12/8
 * @modified
 */
@Service
@Transactional
public class CooperationServiceImpl implements CooperationService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserDao userDao;

    @Autowired
    private CooperationDao cooperationDao;
    @Autowired
    private CooperationApplyDao applyDao;
    @Autowired
    private CooperationMemberDao memberDao;

    @Override
    public ApiDto saveCooperation(CooperationVo cooperationVo) {
        CooperationDo cooperationDo = new CooperationDo();
        BeanUtils.copyProperties(cooperationVo, cooperationDo);
        cooperationDo.setCreateTime(LocalDateTime.now());
        cooperationDao.save(cooperationDo);

        CooperationMemberDo cooperationMemberDo = new CooperationMemberDo();
        cooperationMemberDo.setRole(CooperationMemberDo.CooperationRole.owner);
        cooperationMemberDo.setCooperationId(cooperationDo.getCooperationId());
        cooperationMemberDo.setUserId(userDao.getByUsername((String)SecurityUtils.getSubject().getPrincipal()).getUserId());
        cooperationMemberDo.setJoinTime(cooperationDo.getCreateTime());
        memberDao.save(cooperationMemberDo);

        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        apiDto.setInfo(cooperationDo.getCooperationId());
        return apiDto;
    }

    @Override
    public ApiDto updateCooperation(CooperationVo cooperationVo) {
        CooperationDo cooperationDo = new CooperationDo();
        BeanUtils.copyProperties(cooperationVo, cooperationDo);
        cooperationDo.setCreateTime(LocalDateTime.now());

        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        apiDto.setInfo(cooperationDao.update(cooperationDo));
        return apiDto;
    }

    @Override
    public ApiDto deleteCooperation(Integer cooperationId) {
        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        apiDto.setInfo(cooperationDao.delete(cooperationId));
        return apiDto;
    }

    @Override
    public ApiDto getCooperationById(Integer id) {
        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        apiDto.setInfo(cooperationDao.get(id));
        return apiDto;
    }

    @Override
    public ApiDto listAvailable(MemberAvailableQuery memberAvailableQuery) {
        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        apiDto.setInfo(memberDao.listAvailable(memberAvailableQuery));
        return apiDto;
    }

    @Override
    public ApiDto listCooperation(CooperationSelectQuery cooperationSelectQuery) {
        PageDto<CooperationDto> pageDto = new PageDto<>();
        pageDto.setTotalSize(cooperationDao.count(cooperationSelectQuery.getFilterQuery()));
        if(pageDto.getTotalSize() != 0){
            pageDto.setData(cooperationDao.list(cooperationSelectQuery));
        }

        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        apiDto.setInfo(pageDto);
        return apiDto;
    }

    @Override
    public ApiDto listCooperationTree(Integer cooperationId) {
        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        apiDto.setInfo(cooperationDao.getTree(cooperationId));
        return apiDto;
    }

    @Override
    public ApiDto doApply(Integer cooperationId) {
        ApiDto apiDto = new ApiDto();

        CooperationMemberDo cooperationMemberDo = new CooperationMemberDo();
        cooperationMemberDo.setCooperationId(cooperationId);
        cooperationMemberDo.setUserId(userDao.getByUsername((String)SecurityUtils.getSubject().getPrincipal()).getUserId());

        CooperationDo cooperationDo = cooperationDao.getById(cooperationId);
        if(cooperationDo==null){
            apiDto.setInfo("此协作不存在");
            apiDto.setSuccess(false);
        }else if(memberDao.get(cooperationMemberDo)!=null){
            apiDto.setInfo("您已经是此协作的成员了");
            apiDto.setSuccess(true);
        }else if(cooperationDo.getInvite().equals(false)){
            apiDto.setInfo("邀请链接无效！");
            apiDto.setSuccess(false);
        }else{
            CooperationApplyDo cooperationApplyDo = new CooperationApplyDo();
            cooperationApplyDo.setApplicantId(cooperationMemberDo.getUserId());
            cooperationApplyDo.setCooperationId(cooperationId);
            cooperationApplyDo.setStatus(CooperationApplyDo.ApplyStatus.todo);

            if(applyDao.getApply(cooperationApplyDo)!=null){
                apiDto.setInfo("已申请，等待审核中。。。。");
                apiDto.setSuccess(false);
            }else{
                cooperationApplyDo.setApplyTime(LocalDateTime.now());
                applyDao.save(cooperationApplyDo);
                apiDto.setSuccess(false);
                apiDto.setInfo("申请成功");
            }
        }

        return apiDto;
    }

    @Override
    public ApiDto dealApply(ApplyDealBatchVo applyDealBatchVo) {
        if(applyDealBatchVo.getStatus().equals(CooperationApplyDo.ApplyStatus.pass)){
            applyDao.saveMembers(applyDealBatchVo);
        }

        applyDealBatchVo.setUpdateTime(LocalDateTime.now());
        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        apiDto.setInfo(applyDao.dealApply(applyDealBatchVo));
        return apiDto;
    }

    @Override
    public ApiDto listApply(ApplySelectQuery applySelectQuery) {
        PageDto<CooperationApplyDto> pageDto = new PageDto<>();
        pageDto.setTotalSize(applyDao.count(applySelectQuery.getFilterQuery()));
        if(pageDto.getTotalSize() != 0){
            pageDto.setData(applyDao.list(applySelectQuery));
        }

        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        apiDto.setInfo(pageDto);
        return apiDto;
    }

    @Override
    public ApiDto saveMember(CooperationMemberVo cooperationMemberVo) {
        CooperationMemberDo cooperationMemberDo = new CooperationMemberDo();
        if(cooperationMemberVo.getRole().equals(CooperationMemberDo.CooperationRole.owner)){
            cooperationMemberVo.setRole(CooperationMemberDo.CooperationRole.member);
        }
        BeanUtils.copyProperties(cooperationMemberVo, cooperationMemberDo);
        cooperationMemberDo.setJoinTime(LocalDateTime.now());
        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        apiDto.setInfo(memberDao.save(cooperationMemberDo));
        return apiDto;
    }

    @Override
    public ApiDto changeMemberRole(MemberRoleChangeVo memberRoleChangeVo) {
        CooperationMemberDo cooperationMemberDo = new CooperationMemberDo();
        BeanUtils.copyProperties(memberRoleChangeVo, cooperationMemberDo);

        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        apiDto.setInfo(memberDao.update(cooperationMemberDo));
        return apiDto;
    }

    @Override
    public ApiDto deleteMember(Integer memberId) {
        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        apiDto.setInfo(memberDao.delete(memberId));
        return apiDto;
    }

    @Override
    public ApiDto listMember(MemberSelectQuery memberSelectQuery) {
        PageDto<CooperationMemberDto> pageDto = new PageDto<>();
        pageDto.setTotalSize(memberDao.count(memberSelectQuery.getFilterQuery()));
        if(pageDto.getTotalSize() != 0){
            pageDto.setData(memberDao.list(memberSelectQuery));
        }

        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        apiDto.setInfo(pageDto);
        return apiDto;
    }
}
