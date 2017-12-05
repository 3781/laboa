package team.oha.laboa.controller;

import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import team.oha.laboa.dto.ApiDto;
import team.oha.laboa.service.AgendaService;
import team.oha.laboa.vo.AgendaSummaryVo;
import team.oha.laboa.vo.AgendaVo;

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
}
