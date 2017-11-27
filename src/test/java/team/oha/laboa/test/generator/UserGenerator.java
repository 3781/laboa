package team.oha.laboa.test.generator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import team.oha.laboa.config.AppConfig;
import team.oha.laboa.model.UserDo;
import team.oha.laboa.service.UserService;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * <p>用户数据生成</p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/11/27
 * @modified
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@Transactional
public class UserGenerator {

    @Autowired
    private UserService userService;

    private Random random = new Random();
    private String str = "abcdefghijklmnopqrstuvwxyz";

    final private String PASSWORD = "password";

    @Repeat(value = 50)
    @Rollback(value = false)
    @Test
    public void generatorUser(){
        UserDo userDo = new UserDo();
        StringBuilder username = new StringBuilder();
        while(username.length() < 5 || random.nextBoolean()){
            username.append(str.charAt(random.nextInt(26)));
        }
        userDo.setUsername(username.toString());
        userDo.setPassword(PASSWORD);
        userDo.setRole(random.nextBoolean()? UserDo.Role.admin : UserDo.Role.enduser);
        userDo.setStatus(UserDo.Status.values()[random.nextInt(UserDo.Status.values().length)]);
        userDo.setRegisterTime(LocalDateTime.now()
                .minusWeeks(random.nextInt(20))
                .minusMonths(random.nextInt(40))
                .minusDays(random.nextInt(100))
                .minusHours(random.nextInt(100))
                .minusMinutes(random.nextInt(100))
                .minusSeconds(random.nextInt(100))

        );
        userDo.setLoginTime(LocalDateTime.from(userDo.getRegisterTime())
                .plusWeeks(random.nextInt(20))
                .plusMonths(random.nextInt(40))
                .plusDays(random.nextInt(100))
                .plusHours(random.nextInt(100))
                .plusMinutes(random.nextInt(100))
                .plusSeconds(random.nextInt(100))
        );
        userService.saveUser(userDo);
    }
}
