package team.oha.laboa.test.dao;

import org.junit.Assert;
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
import team.oha.laboa.model.UserDo;

import java.time.LocalDateTime;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/11/27
 * @modified
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@Transactional
public class UserDaoTest {
    @Autowired
    private UserDao userDao;
    private UserDo userDo;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void saveTest(){
        userDo = new UserDo();
        userDo.setUsername("username");
        userDo.setPassword("password");
        userDo.setSalt("sdfasdfsdfsdaf");
        userDo.setRole(UserDo.Role.enduser);
        userDo.setStatus(UserDo.Status.normal);
        userDo.setLoginTime(LocalDateTime.now());
        userDo.setRegisterTime(LocalDateTime.now());
        Assert.assertSame(userDao.save(userDo), 1);
        Assert.assertNotNull(userDo.getUserId());
        logger.debug("[{}]", userDo);
    }

    @Test
    public void getByUsernameTest(){
        String username = "ibiqqnxr";
        UserDo userDoToFind = userDao.getByUsername(userDo.getUsername());
        Assert.assertNotNull(userDoToFind);
        logger.debug("[{}]", userDoToFind);
    }
}
