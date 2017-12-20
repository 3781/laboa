package team.oha.laboa.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import team.oha.laboa.config.AppConfig;
import team.oha.laboa.service.UserService;
import team.oha.laboa.vo.RegisterVo;

/**
 * <p>用户服务测试</p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/11/27
 * @modified
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@Transactional
public class UserServiceTest {
    @Autowired
    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Rollback(false)
    @Test
    public void saveTest(){
        RegisterVo registerVo = new RegisterVo();
        registerVo.setUsername("username");
        registerVo.setPassword("password");
        registerVo.setEmail("email2@qq.com");
        registerVo.setName("王大锤2");
        registerVo.setEmployeeNumber("S012234542");
        registerVo.setQq("32134511112");
        registerVo.setPhone("18654033332");
        userService.register(registerVo);
    }

    @Test
    public void updateTimeTest(){
//        userService.updateLoginTime("hobrc");
    }
}
