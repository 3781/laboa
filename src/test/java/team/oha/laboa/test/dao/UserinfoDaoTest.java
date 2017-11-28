package team.oha.laboa.test.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import team.oha.laboa.config.AppConfig;
import team.oha.laboa.dao.UserDao;
import team.oha.laboa.dao.UserinfoDao;
import team.oha.laboa.model.UserDo;
import team.oha.laboa.model.UserinfoDo;

import java.time.LocalDateTime;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/11/28
 * @modified
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@Transactional
public class UserinfoDaoTest {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserinfoDao userinfoDao;
    @Autowired
    private UserDao userDao;

    private Integer userId;

    @Before
    public void initUser(){
        UserDo userDo = new UserDo();
        userDo.setUsername("username");
        userDo.setPassword("password");
        userDo.setSalt("sdfasdsdaf");
        userDo.setRole(UserDo.Role.admin);
        userDo.setStatus(UserDo.Status.normal);
        userDo.setLoginTime(LocalDateTime.now());
        userDo.setRegisterTime(LocalDateTime.now());
        userDao.save(userDo);

        userId = userDo.getUserId();
    }

    @Test
    public void saveUserinfo(){
        UserinfoDo userinfoDo = new UserinfoDo();
        userinfoDo.setUserId(userId);
        userinfoDo.setEmail("2131324@qq.com");
        userinfoDo.setEmployeeNumber("S4554531");
        userinfoDo.setName("安圣基");
        userinfoDo.setPhone("18650456756");
        userinfoDo.setQq("2345465897");
        Assert.assertSame(userinfoDao.save(userinfoDo), 1);
        Assert.assertNotNull(userinfoDo.getUserId());
        logger.debug("[{}]", userinfoDo);
    }
}
