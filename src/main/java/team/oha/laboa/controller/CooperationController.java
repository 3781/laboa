package team.oha.laboa.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import team.oha.laboa.dto.ApiDto;
import team.oha.laboa.model.CooperationMemberDo;
import team.oha.laboa.query.agenda.AgendaFilterQuery;
import team.oha.laboa.query.agenda.AgendaSelectQuery;
import team.oha.laboa.query.cooperation.CooperationFilterQuery;
import team.oha.laboa.query.cooperation.CooperationSelectQuery;
import team.oha.laboa.query.cooperation.apply.ApplySelectQuery;
import team.oha.laboa.query.cooperation.member.MemberAvailableQuery;
import team.oha.laboa.query.cooperation.member.MemberFilterQuery;
import team.oha.laboa.query.cooperation.member.MemberSelectQuery;
import team.oha.laboa.service.AgendaService;
import team.oha.laboa.service.CooperationService;
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
@RestController
@RequestMapping("/api/cooperation")
public class CooperationController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CooperationService cooperationService;
    @Autowired
    private AgendaService agendaService;

    /**
     * <p>分页协作日程列表</p>
     *
     * @author loser
     * @version 1.0
     * @data 2017/11/27
     * @modified
     */
    @RequiresUser
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{cooperationId:[1-9][0-9]*}/agenda")
    public ApiDto cooperationAgenda(AgendaSelectQuery agendaSelectQuery, @PathVariable Integer cooperationId){
        if( agendaSelectQuery.getFilterQuery() == null){
            agendaSelectQuery.setFilterQuery(new AgendaFilterQuery());
        }
        agendaSelectQuery.getFilterQuery().setCooperationId(cooperationId);
        return agendaService.listAgendas(agendaSelectQuery);
    }

    @RequiresUser
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user")
    public ApiDto listAvailable(MemberAvailableQuery memberAvailableQuery) {
        return cooperationService.listAvailable(memberAvailableQuery);
    }

    @RequiresUser
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/apply/{cooperationId:[1-9][0-9]*}")
    public ApiDto doApply(@PathVariable Integer cooperationId) {
        return cooperationService.doApply(cooperationId);
    }

    @RequiresUser
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ApiDto saveCooperation(@RequestBody CooperationVo cooperationVo) {
        return cooperationService.saveCooperation(cooperationVo);
    }

    @RequiresUser
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping
    public ApiDto updateCooperation(@RequestBody CooperationVo cooperationVo) {
        return cooperationService.updateCooperation(cooperationVo);
    }

    @RequiresUser
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{cooperationId:[1-9][0-9]*}")
    public ApiDto deleteCooperation(@PathVariable Integer cooperationId) {
        return cooperationService.deleteCooperation(cooperationId);
    }

    @RequiresUser
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{cooperationId:[1-9][0-9]*}")
    public ApiDto getCooperation(@PathVariable Integer cooperationId) {
        return cooperationService.getCooperationById(cooperationId);
    }

    @RequiresUser
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{cooperationId:[1-9][0-9]*}/tree")
    public ApiDto listTree(@PathVariable Integer cooperationId) {
        return cooperationService.listCooperationTree(cooperationId);
    }

    @RequiresUser
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{role:owner|manager|member}")
    public ApiDto listCooperation(CooperationSelectQuery cooperationSelectQuery, @PathVariable("role") CooperationMemberDo.CooperationRole role){
        if(cooperationSelectQuery.getFilterQuery()==null){
            cooperationSelectQuery.setFilterQuery(new CooperationFilterQuery());
        }

        cooperationSelectQuery.getFilterQuery().setUsername((String)SecurityUtils.getSubject().getPrincipal());
        cooperationSelectQuery.getFilterQuery().setRole(role);

        return cooperationService.listCooperation(cooperationSelectQuery);
    }

    @RequiresUser
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/members")
    public ApiDto listMember(MemberSelectQuery memberSelectQuery) {
        if(memberSelectQuery.getFilterQuery()==null){
            memberSelectQuery.setFilterQuery(new MemberFilterQuery());
        }
        return cooperationService.listMember(memberSelectQuery);
    }

    @RequiresUser
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/member")
    public ApiDto saveMember(@RequestBody CooperationMemberVo cooperationMemberVo) {
        return cooperationService.saveMember(cooperationMemberVo);
    }

    @RequiresUser
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/member")
    public ApiDto updateMember(@RequestBody MemberRoleChangeVo memberRoleChangeVo) {
        return cooperationService.changeMemberRole(memberRoleChangeVo);
    }

    @RequiresUser
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/member/{memberId:[1-9][0-9]*}")
    public ApiDto deleteMember(@PathVariable Integer memberId) {
        return cooperationService.deleteMember(memberId);
    }

    @RequiresUser
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/apply")
    public ApiDto listApply(ApplySelectQuery applySelectQuery) {
        return cooperationService.listApply(applySelectQuery);
    }

    @RequiresUser
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/apply")
    public ApiDto dealApply(@RequestBody ApplyDealBatchVo applyDealBatchVo) {
        return cooperationService.dealApply(applyDealBatchVo);
    }
}
