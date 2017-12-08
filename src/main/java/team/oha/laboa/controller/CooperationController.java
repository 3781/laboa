package team.oha.laboa.controller;

import org.apache.shiro.authz.annotation.RequiresUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import team.oha.laboa.dto.ApiDto;
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
}
