package team.oha.laboa.service.impl;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.oha.laboa.dao.AgendaDao;
import team.oha.laboa.dao.AgendaItemDao;
import team.oha.laboa.dao.AgendaSummaryDao;
import team.oha.laboa.dao.UserDao;
import team.oha.laboa.dto.ApiDto;
import team.oha.laboa.model.AgendaDo;
import team.oha.laboa.model.AgendaItemDo;
import team.oha.laboa.model.AgendaSummaryDo;
import team.oha.laboa.service.AgendaService;
import team.oha.laboa.vo.AgendaSummaryVo;
import team.oha.laboa.vo.AgendaVo;

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
    private AgendaDao agendaDao;
    @Autowired
    private AgendaItemDao agendaItemDao;
    @Autowired
    private AgendaSummaryDao agendaSummaryDao;
    @Autowired
    private UserDao userDao;

    @Override
    public ApiDto savePersonalAgenda(AgendaVo agendaVo) {
        System.out.println(agendaVo);

        AgendaDo agendaDo = new AgendaDo();

        BeanUtils.copyProperties(agendaVo, agendaDo);
        agendaDo.setCreateTime(LocalDateTime.now());
        agendaDo.setOwnerId(userDao.getByUsername((String)SecurityUtils.getSubject().getPrincipal()).getUserId());
        agendaDo.setType(AgendaDo.AgendaType.personal);
        Integer agendaId = agendaDao.save(agendaDo);

        AgendaItemDo agendaItemDo = new AgendaItemDo();
        agendaItemDo.setAgendaId(agendaId);
        agendaItemDo.setSummaryTime(agendaDo.getNextTime());
        agendaItemDao.save(agendaItemDo);

        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        return apiDto;
    }


    @Override
    public ApiDto summaryAgenda(AgendaSummaryVo agendaSummaryVo) {
        AgendaSummaryDo agendaSummaryDo = new AgendaSummaryDo();
        BeanUtils.copyProperties(agendaSummaryVo, agendaSummaryDo);
        agendaSummaryDo.setSummaryTime(LocalDateTime.now());

        agendaSummaryDao.save(agendaSummaryDo);

        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        return apiDto;
    }
}
