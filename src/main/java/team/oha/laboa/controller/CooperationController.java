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
import team.oha.laboa.query.cooperation.CooperationFilterQuery;
import team.oha.laboa.query.cooperation.CooperationSelectQuery;
import team.oha.laboa.query.cooperation.member.MemberAvailableQuery;
import team.oha.laboa.service.CooperationService;
import team.oha.laboa.vo.CooperationVo;

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

    @RequiresUser
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user")
    public ApiDto listAvailable(MemberAvailableQuery memberAvailableQuery) {
        return cooperationService.listAvailable(memberAvailableQuery);
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
    public ApiDto listOwn(CooperationSelectQuery cooperationSelectQuery, @PathVariable("role") CooperationMemberDo.CooperationRole role){
        if(cooperationSelectQuery.getFilterQuery()==null){
            cooperationSelectQuery.setFilterQuery(new CooperationFilterQuery());
        }

        cooperationSelectQuery.getFilterQuery().setUsername((String)SecurityUtils.getSubject().getPrincipal());
        cooperationSelectQuery.getFilterQuery().setRole(role);

        return cooperationService.listCooperation(cooperationSelectQuery);
    }
}
