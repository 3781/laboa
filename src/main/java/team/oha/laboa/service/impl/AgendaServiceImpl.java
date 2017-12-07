package team.oha.laboa.service.impl;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.oha.laboa.dao.AgendaDao;
import team.oha.laboa.dao.AgendaItemDao;
import team.oha.laboa.dao.AgendaSummaryDao;
import team.oha.laboa.dao.UserDao;
import team.oha.laboa.dto.AgendaDto;
import team.oha.laboa.dto.ApiDto;
import team.oha.laboa.dto.PageDto;
import team.oha.laboa.model.AgendaDo;
import team.oha.laboa.model.AgendaItemDo;
import team.oha.laboa.model.AgendaSummaryDo;
import team.oha.laboa.query.agenda.AgendaSelectQuery;
import team.oha.laboa.query.agenda.AgendaToDoQuery;
import team.oha.laboa.service.AgendaService;
import team.oha.laboa.vo.AgendaSummaryVo;
import team.oha.laboa.vo.AgendaVo;
import team.oha.laboa.vo.BatchVo;

import java.time.LocalDateTime;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/12/5
 * @modified
 */
@Service
public class AgendaServiceImpl implements AgendaService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private AgendaItemDao agendaItemDao;
    @Autowired
    private AgendaSummaryDao agendaSummaryDao;
    @Autowired
    private AgendaDao agendaDao;

    @Override
    public ApiDto savePersonalAgenda(AgendaVo agendaVo) {
        System.out.println(agendaVo);
        Integer ownerId = userDao.getByUsername((String)SecurityUtils.getSubject().getPrincipal()).getUserId();
        AgendaDo agendaDo = new AgendaDo();

        BeanUtils.copyProperties(agendaVo, agendaDo);
        agendaDo.setCreateTime(LocalDateTime.now());
        agendaDo.setOwnerId(ownerId);
        agendaDo.setType(AgendaDo.AgendaType.personal);
        agendaDao.save(agendaDo);

        AgendaItemDo agendaItemDo = new AgendaItemDo();
        agendaItemDo.setAgendaId(agendaDo.getAgendaId());
        agendaItemDo.setSummaryTime(agendaDo.getNextTime());
        agendaItemDao.save(agendaItemDo);

        AgendaSummaryDo agendaSummaryDo = new AgendaSummaryDo();
        agendaSummaryDo.setItemId(agendaItemDo.getItemId());
        agendaSummaryDo.setSummarizerId(ownerId);
        agendaSummaryDo.setStatus(AgendaSummaryDo.SummaryStatus.todo);
        agendaSummaryDao.save(agendaSummaryDo);

        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        return apiDto;
    }


    @Override
    public ApiDto summaryAgenda(AgendaSummaryVo agendaSummaryVo) {
        AgendaSummaryDo agendaSummaryDo = new AgendaSummaryDo();
        BeanUtils.copyProperties(agendaSummaryVo, agendaSummaryDo);
        agendaSummaryDo.setSummaryTime(LocalDateTime.now());

        agendaSummaryDao.update(agendaSummaryDo);

        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        return apiDto;
    }


    @Override
    public ApiDto updateSummary(AgendaSummaryVo agendaSummaryVo) {
        AgendaSummaryDo agendaSummaryDo = new AgendaSummaryDo();
        BeanUtils.copyProperties(agendaSummaryVo, agendaSummaryDo);
        agendaSummaryDo.setUpdateTime(LocalDateTime.now());

        agendaSummaryDao.update(agendaSummaryDo);

        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        return apiDto;
    }

    @Override
    public ApiDto listAgendas(AgendaSelectQuery agendaSelectQuery) {
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
    public ApiDto close(BatchVo batchVo) {
        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        apiDto.setInfo(agendaDao.close(batchVo));
        return apiDto;
    }

    @Override
    public ApiDto update(AgendaVo agendaVo) {
        AgendaDo agendaDo = new AgendaDo();

        BeanUtils.copyProperties(agendaVo, agendaDo);
        agendaDo.setUpdateTime(LocalDateTime.now());
        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        apiDto.setInfo(agendaDao.update(agendaDo));
        return apiDto;
    }
}
