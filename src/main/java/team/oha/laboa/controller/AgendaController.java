package team.oha.laboa.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import team.oha.laboa.dto.ApiDto;
import team.oha.laboa.query.agenda.AgendaSelectQuery;
import team.oha.laboa.query.agenda.AgendaToDoQuery;
import team.oha.laboa.service.AgendaService;
import team.oha.laboa.vo.AgendaBatchVo;
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
@RestController
@RequestMapping("/agenda")
public class AgendaController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AgendaService agendaService;

    @RequiresUser
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public ApiDto saveAgenda(@RequestBody AgendaVo agendaVo) {
        return agendaService.saveAgenda(agendaVo);
    }

    @RequiresUser
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping
    public ApiDto deleteAgenda(AgendaBatchVo agendaBatchVo) {
        return agendaService.deleteAgendas(agendaBatchVo);
    }

    @RequiresUser
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(params = "open")
    public ApiDto closeAgenda(@RequestBody AgendaBatchVo agendaBatchVo, Boolean open) {
        return open ? agendaService.openAgendas(agendaBatchVo) : agendaService.closeAgendas(agendaBatchVo);
    }


    @RequiresUser
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/summary")
    public ApiDto summaryAgenda(@RequestBody AgendaSummaryVo agendaSummaryVo) {
        return agendaService.summaryAgenda(agendaSummaryVo);
    }

    /**
     * <p>分页我的日程列表</p>
     *
     * @author loser
     * @version 1.0
     * @data 2017/11/27
     * @modified
     */
    @RequiresUser
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/own")
    public ApiDto ownList(AgendaSelectQuery agendaSelectQuery){
        return agendaService.listOwnAgendas(agendaSelectQuery);
    }

    /**
     * <p>分页参与日程列表</p>
     *
     * @author loser
     * @version 1.0
     * @data 2017/11/27
     * @modified
     */
    @RequiresUser
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/join")
    public ApiDto joinList(AgendaSelectQuery agendaSelectQuery){
        return agendaService.listJoinAgendas(agendaSelectQuery);
    }

    /**
     * <p>更新日程</p>
     *
     * @author loser
     * @version 1.0
     * @data 2017/11/27
     * @modified
     */
    @RequiresUser
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping
    public ApiDto update(@RequestBody AgendaVo agendaVo) {
        return agendaService.update(agendaVo);
    }

    /**
     * <p>待办事项</p>
     *
     * @author loser
     * @version 1.0
     * @data 2017/11/27
     * @modified
     */
    @RequiresUser
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/todo")
    public ApiDto listToDo(AgendaToDoQuery agendaToDoQuery) {
        agendaToDoQuery.setUsername((String) SecurityUtils.getSubject().getPrincipal());
        return agendaService.listToDo(agendaToDoQuery);
    }

    @RequiresUser
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{agendaId:[1-9][0-9]*}")
    public ApiDto getAgendaDetail(@PathVariable Integer agendaId) {
        return agendaService.getAgendaDetail(agendaId);
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void refreshAgendaJob(){
        agendaService.refreshAgenda();
        logger.info("[{}]生成日程成功", LocalDateTime.now());
    }
}
