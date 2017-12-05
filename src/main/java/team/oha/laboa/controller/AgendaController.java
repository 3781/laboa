package team.oha.laboa.controller;

import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import team.oha.laboa.dto.ApiDto;
import team.oha.laboa.query.agenda.AgendaSelectQuery;
import team.oha.laboa.service.AgendaService;
import team.oha.laboa.vo.AgendaSummaryVo;
import team.oha.laboa.vo.AgendaVo;
import team.oha.laboa.vo.BatchVo;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/12/5
 * @modified
 */
@RestController
@RequestMapping("/api/agenda")
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @RequiresUser
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public ApiDto saveAgenda(@RequestBody AgendaVo agendaVo) {
        return agendaService.savePersonalAgenda(agendaVo);
    }

    @RequiresUser
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/summary")
    public ApiDto summaryAgenda(@RequestBody AgendaSummaryVo agendaSummaryVo) {
        return agendaService.summaryAgenda(agendaSummaryVo);
    }

    /**
     * <p>关闭日程</p>
     *
     * @author loser
     * @version 1.0
     * @data 2017/11/27
     * @modified
     */
    @RequiresUser
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping
    public ApiDto close(BatchVo batchVo){
        return agendaService.close(batchVo);
    }

    /**
     * <p>分页日程列表</p>
     *
     * @author loser
     * @version 1.0
     * @data 2017/11/27
     * @modified
     */
    @RequiresUser
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/list")
    public ApiDto list(AgendaSelectQuery agendaSelectQuery){
        return agendaService.listAgendas(agendaSelectQuery);
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
}
