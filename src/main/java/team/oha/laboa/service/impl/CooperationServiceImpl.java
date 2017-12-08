package team.oha.laboa.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.oha.laboa.dao.CooperationApplyDao;
import team.oha.laboa.dao.CooperationDao;
import team.oha.laboa.dao.CooperationMemberDao;
import team.oha.laboa.dto.*;
import team.oha.laboa.query.cooperation.CooperationSelectQuery;
import team.oha.laboa.query.cooperation.apply.ApplySelectQuery;
import team.oha.laboa.query.cooperation.member.MemberAvailableQuery;
import team.oha.laboa.query.cooperation.member.MemberSelectQuery;
import team.oha.laboa.service.CooperationService;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/12/8
 * @modified
 */
@Service
public class CooperationServiceImpl implements CooperationService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CooperationDao cooperationDao;
    @Autowired
    private CooperationApplyDao applyDao;
    @Autowired
    private CooperationMemberDao memberDao;

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
