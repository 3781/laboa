package team.oha.laboa.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import team.oha.laboa.util.password.HashEncryptor;
import team.oha.laboa.util.password.Md5Encryptor;
import team.oha.laboa.util.password.RandomSaltGenerator;
import team.oha.laboa.util.password.SaltGenerator;

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
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public SaltGenerator saltGenerator(){
        return RandomSaltGenerator.getInstance();
    }

    @Bean
    public HashEncryptor hashEncryptor(@Value("${hashIterations}") int hashIterations){
        return new Md5Encryptor(hashIterations);
    }
}
