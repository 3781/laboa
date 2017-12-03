package team.oha.laboa.test.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import team.oha.laboa.dto.UserDto;
import team.oha.laboa.model.UserDo;
import team.oha.laboa.query.OrderQuery;
import team.oha.laboa.query.PageQuery;
import team.oha.laboa.query.user.UserFilterQuery;
import team.oha.laboa.query.user.UserOrderQuery;
import team.oha.laboa.query.user.UserSelectQuery;

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
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserDao userDao;

    @Test
    public void saveTest(){
        UserDo userDo = new UserDo();
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
        UserDo userDoToFind = userDao.getByUsername(username);
        Assert.assertNotNull(userDoToFind);
        logger.debug("[{}]", userDoToFind);
    }

    @Test
    public void countTest() {
        UserFilterQuery filterQuery = new UserFilterQuery();
        filterQuery.setUsername("ob");
        logger.debug("[{}]", userDao.count(filterQuery));
    }

    @Test
    public void listTest() throws Exception{
        UserSelectQuery userSelectQuery = new UserSelectQuery();
        UserFilterQuery filterQuery= new UserFilterQuery();
        filterQuery.setUsername("b");
        userSelectQuery.setFilterQuery(filterQuery);

        UserOrderQuery orderQuery = new UserOrderQuery();
        orderQuery.setOrder(OrderQuery.Order.asc);
        orderQuery.setField(UserOrderQuery.SortField.email);
        userSelectQuery.setOrderQuery(orderQuery);

        PageQuery pageQuery = new PageQuery();
        pageQuery.setOffset(0);
        pageQuery.setRows(10);
        userSelectQuery.setPageQuery(pageQuery);

        for(UserDto userDto: userDao.list(userSelectQuery)){
            logger.debug("[{}]", new ObjectMapper().writeValueAsString(userDto));
        }
    }
}
