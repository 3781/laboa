package team.oha.laboa.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import team.oha.laboa.config.AppConfig;
import team.oha.laboa.model.UserDo;
import team.oha.laboa.service.UserService;

import java.time.LocalDateTime;

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

    @Test
    public void saveTest(){
        UserDo userDo = new UserDo();
        userDo.setUsername("username");
        userDo.setPassword("password");
        userDo.setRole(UserDo.Role.enduser);
        userDo.setStatus(UserDo.Status.normal);
        userDo.setLoginTime(LocalDateTime.now());
        userDo.setRegisterTime(LocalDateTime.now());
        userService.saveUser(userDo);
    }

    @Test
    public void updateTimeTest(){
        userService.updateLoginTime("hobrc");
    }
}
