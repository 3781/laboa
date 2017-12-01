package team.oha.laboa.config;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.config.ShiroBeanConfiguration;
import org.apache.shiro.spring.web.config.ShiroWebFilterConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import team.oha.laboa.shiro.realm.UserRealm;
import team.oha.laboa.shiro.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/11/26
 * @modified
 */
@Configuration
@Import({
        ShiroBeanConfiguration.class,
        ShiroWebConfiguration.class,
        ShiroWebFilterConfiguration.class,
})
public class SecurityConfig {

    @Value("${hashAlgorithmName}")
    String hashAlgorithmName;
    @Value("${hashIterations}")
    Integer hashIterations;
    @Value("${storedCredentialsHexEncoded}")
    Boolean storedCredentialsHexEncoded;

    @Bean
    public CredentialsMatcher credentialsMatcher(){
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName(hashAlgorithmName);
        credentialsMatcher.setHashIterations(hashIterations);
        credentialsMatcher.setStoredCredentialsHexEncoded(storedCredentialsHexEncoded);
        return credentialsMatcher;
    }

    @Bean
    public List<Realm> realms(UserService simpleAccountService){
        return new ArrayList<Realm>(){{
            add(new UserRealm(credentialsMatcher(), simpleAccountService));
        }};
    }

    @Bean
    public Object setSecurityManager(SecurityManager securityManager) throws Exception{
        MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        methodInvokingFactoryBean.setArguments(securityManager);
        return methodInvokingFactoryBean;
    }
}
