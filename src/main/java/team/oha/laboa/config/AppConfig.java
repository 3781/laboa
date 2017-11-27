package team.oha.laboa.config;

import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/11/26
 * @modified
 */
@Configuration
@ComponentScans({
        @ComponentScan(value = "team.oha.laboa.service.impl"),
        @ComponentScan(value = "team.oha.laboa.dao")
})
@EnableTransactionManagement
@Import({
        MybatisConfig.class,
        SecurityConfig.class
})
@PropertySource(value = "classpath:app.properties", encoding = "UTF-8")
public class AppConfig {
}
