package team.oha.laboa.service.impl;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.oha.laboa.dao.*;
import team.oha.laboa.dto.AgendaDto;
import team.oha.laboa.dto.ApiDto;
import team.oha.laboa.dto.PageDto;
import team.oha.laboa.model.*;
import team.oha.laboa.query.agenda.AgendaFilterQuery;
import team.oha.laboa.query.agenda.AgendaSelectQuery;
import team.oha.laboa.query.agenda.AgendaToDoQuery;
import team.oha.laboa.service.AgendaService;
import team.oha.laboa.vo.AgendaBatchVo;
import team.oha.laboa.vo.AgendaSummaryVo;
import team.oha.laboa.vo.AgendaVo;
import team.oha.laboa.vo.CooperationAgendaParticipantVo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/12/5
 * @modified
 */
@Service
@Transactional
public class AgendaServiceImpl implements AgendaService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserDao userDao;
    @Autowired
    private AgendaItemDao agendaItemDao;
    @Autowired
    private AgendaSummaryDao agendaSummaryDao;
    @Autowired
    private AgendaDao agendaDao;
    @Autowired
    private CooperationAgendaParticipantDao cooperationAgendaParticipantDao;
    @Autowired
    private CooperationAgendaDao cooperationAgendaDao;

    private void saveCooperationAgendaRelation(Integer cooperationId, Integer agendaId,Integer[] memberIds){
        CooperationAgendaDo cooperationAgendaDo = new CooperationAgendaDo();
        cooperationAgendaDo.setAgendaId(agendaId);
        cooperationAgendaDo.setCooperationId(cooperationId);
        cooperationAgendaDao.save(cooperationAgendaDo);

        cooperationAgendaParticipantDao.saveBatch(cooperationAgendaDo.getCooperationAgendaId(), memberIds);
    }

    private void generateAgendaItem(AgendaDo agendaDo) {
        AgendaItemDo agendaItemDo = new AgendaItemDo();
        agendaItemDo.setAgendaId(agendaDo.getAgendaId());
        agendaItemDo.setSummaryTime(agendaDo.getNextTime());
        agendaItemDao.save(agendaItemDo);
        if(agendaDo.getType().equals(AgendaDo.AgendaType.cooperation)){
            agendaItemDao.generateCooperationSummary(agendaItemDo);
        }else{
            agendaItemDao.generatePersonalSummary(agendaItemDo);
        }
    }

    @Override
    public ApiDto saveAgenda(AgendaVo agendaVo) {
        ApiDto apiDto = new ApiDto();
        if(agendaVo.getNextTime().isBefore(LocalDateTime.now())){
            apiDto.setSuccess(false);
            apiDto.setInfo("下次执行时间要晚于现在！");
        }else{
            Integer ownerId = userDao.getByUsername((String)SecurityUtils.getSubject().getPrincipal()).getUserId();
            AgendaDo agendaDo = new AgendaDo();

            BeanUtils.copyProperties(agendaVo, agendaDo);
            agendaDo.setCreateTime(LocalDateTime.now());
            agendaDo.setOwnerId(ownerId);
            agendaDo.setOpen(true);
            agendaDo.setType(agendaVo.getCooperationId()!=null
                    ? AgendaDo.AgendaType.cooperation:AgendaDo.AgendaType.personal);
            agendaDao.save(agendaDo);

            if(agendaDo.getType().equals(AgendaDo.AgendaType.cooperation)){
                saveCooperationAgendaRelation(agendaVo.getCooperationId(), agendaDo.getAgendaId(), agendaVo.getMemberIds());
            }

            generateAgendaItem(agendaDo);
            apiDto.setSuccess(true);
            apiDto.setInfo(agendaDo.getAgendaId());
        }


        return apiDto;
    }

    @Override
    public ApiDto deleteAgendas(AgendaBatchVo agendaBatchVo) {
        agendaBatchVo.setUserId(userDao.getByUsername((String)SecurityUtils.getSubject().getPrincipal()).getUserId());
        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        apiDto.setInfo(agendaDao.deleteBatch(agendaBatchVo));
        return apiDto;
    }

    @Override
    public ApiDto closeAgendas(AgendaBatchVo agendaBatchVo) {
        agendaBatchVo.setUserId(userDao.getByUsername((String)SecurityUtils.getSubject().getPrincipal()).getUserId());
        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        apiDto.setInfo(agendaDao.closeBatch(agendaBatchVo));
        agendaItemDao.cleanCloseAgendaItem(agendaBatchVo);
        return apiDto;
    }

    @Override
    public ApiDto openAgendas(AgendaBatchVo agendaBatchVo) {
        agendaBatchVo.setUserId(userDao.getByUsername((String)SecurityUtils.getSubject().getPrincipal()).getUserId());
        agendaBatchVo.setNowTime(LocalDateTime.now());
        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        apiDto.setInfo(agendaDao.openBatch(agendaBatchVo));

        for(AgendaDo agendaDo : agendaDao.listNeedGenerateItemAgenda()){
            generateAgendaItem(agendaDo);
        }

        return apiDto;
    }

    @Override
    public ApiDto summaryAgenda(AgendaSummaryVo agendaSummaryVo) {
        AgendaSummaryDo agendaSummaryDo = new AgendaSummaryDo();
        BeanUtils.copyProperties(agendaSummaryVo, agendaSummaryDo);
        agendaSummaryDo.setSummaryTime(LocalDateTime.now());
        agendaSummaryDo.setSummarizerId(userDao.getByUsername((String)SecurityUtils.getSubject().getPrincipal()).getUserId());

        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        apiDto.setInfo(agendaSummaryDao.update(agendaSummaryDo));
        return apiDto;
    }

    @Override
    public ApiDto listOwnAgendas(AgendaSelectQuery agendaSelectQuery) {
        if( agendaSelectQuery.getFilterQuery() == null){
            agendaSelectQuery.setFilterQuery(new AgendaFilterQuery());
        }
        agendaSelectQuery.getFilterQuery().setOwnerId(
                userDao.getByUsername((String)SecurityUtils.getSubject().getPrincipal()).getUserId()
        );

        PageDto<AgendaDto> pageDto = new PageDto<>();
        pageDto.setTotalSize(agendaDao.count(agendaSelectQuery.getFilterQuery()));
        if(pageDto.getTotalSize() != 0){
            pageDto.setData(agendaDao.list(agendaSelectQuery));
        }

        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        apiDto.setInfo(pageDto);
        return apiDto;
    }

    @Override
    public ApiDto listJoinAgendas(AgendaSelectQuery agendaSelectQuery) {
        if( agendaSelectQuery.getFilterQuery() == null){
            agendaSelectQuery.setFilterQuery(new AgendaFilterQuery());
        }
        agendaSelectQuery.getFilterQuery().setSummarizerId(
                userDao.getByUsername((String)SecurityUtils.getSubject().getPrincipal()).getUserId()
        );

        PageDto<AgendaDto> pageDto = new PageDto<>();
        pageDto.setTotalSize(agendaDao.count(agendaSelectQuery.getFilterQuery()));
        if(pageDto.getTotalSize() != 0){
            pageDto.setData(agendaDao.list(agendaSelectQuery));
        }

        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        apiDto.setInfo(pageDto);
        return apiDto;
    }

    @Override
    public ApiDto listToDo(AgendaToDoQuery agendaToDoQuery) {
        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        apiDto.setInfo(agendaSummaryDao.listToDo(agendaToDoQuery));
        return apiDto;
    }

    @Override
    public ApiDto update(AgendaVo agendaVo) {
        ApiDto apiDto = new ApiDto();
        if(agendaVo.getNextTime().isBefore(LocalDateTime.now())){
            apiDto.setSuccess(false);
            apiDto.setInfo("下次执行时间要晚于现在！");
        }else{
            AgendaItemDo agendaItemDo = new AgendaItemDo();
            agendaItemDo.setAgendaId(agendaVo.getAgendaId());
            agendaItemDo.setSummaryTime(agendaVo.getNextTime());
            agendaItemDao.updateSummaryTime(agendaItemDo);

            AgendaDo agendaDo = new AgendaDo();
            BeanUtils.copyProperties(agendaVo, agendaDo);
            agendaDo.setOpen(null);
            agendaDo.setUpdateTime(LocalDateTime.now());

            apiDto.setSuccess(true);
            apiDto.setInfo(agendaDao.update(agendaDo));
        }

        return apiDto;
    }

    @Override
    public ApiDto saveCooperationAgendaParticipant(CooperationAgendaParticipantVo cooperationAgendaParticipantVo) {
        CooperationAgendaParticipantDo cooperationAgendaParticipantDo = new CooperationAgendaParticipantDo();
        BeanUtils.copyProperties(cooperationAgendaParticipantVo, cooperationAgendaParticipantDo);
        cooperationAgendaParticipantDao.save(cooperationAgendaParticipantDo);

        agendaSummaryDao.saveByParticipantId(cooperationAgendaParticipantDo.getParticipantId());

        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        apiDto.setInfo(cooperationAgendaParticipantDo.getParticipantId());
        return apiDto;
    }

    @Override
    public ApiDto deleteCooperationAgendaParticipant(Integer participantId) {
        agendaSummaryDao.deleteByParticipantId(participantId);
        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        apiDto.setInfo(cooperationAgendaParticipantDao.delete(participantId));
        return apiDto;
    }

    @Override
    public ApiDto getAgendaDetail(Integer agendaId) {
        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        apiDto.setInfo(agendaDao.getAgendaDetail(agendaId));
        return apiDto;
    }

    @Override
    public void refreshAgenda() {
        AgendaDo agenda = new AgendaDo();
        List<AgendaDo> agendaDoList = agendaDao.listNeedReGenerateItemAgenda(LocalDateTime.of(LocalDate.now(), LocalTime.MIN));
        if(agendaDoList!=null){
            for(AgendaDo agendaDo: agendaDoList){
                agenda.setAgendaId(agendaDo.getAgendaId());
                switch (agendaDo.getUnit()){
                    case day:
                        agenda.setNextTime(agendaDo.getNextTime().plusDays(agendaDo.getQuantity()));
                        break;
                    case week:
                        agenda.setNextTime(agendaDo.getNextTime().plusWeeks(agendaDo.getQuantity()));
                        break;
                    case month:
                        agenda.setNextTime(agendaDo.getNextTime().plusMonths(agendaDo.getQuantity()));
                        break;
                    default:
                }

                if(!agendaDo.getUnit().equals(AgendaDo.AgendaUnit.once)){
                    agendaDao.update(agenda);
                    generateAgendaItem(agenda);
                }
            }
        }
    }
}
