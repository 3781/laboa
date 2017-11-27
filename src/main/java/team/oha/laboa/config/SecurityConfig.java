package team.oha.laboa.config;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.config.ShiroBeanConfiguration;
import org.apache.shiro.spring.config.ShiroConfiguration;
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
        ShiroConfiguration.class
})
public class SecurityConfig {

    @Bean
    public CredentialsMatcher credentialsMatcher(
            @Value("${hashAlgorithmName}") String hashAlgorithmName,
            @Value("${hashIterations}") Integer hashIterations,
            @Value("${storedCredentialsHexEncoded}") Boolean storedCredentialsHexEncoded){
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName(hashAlgorithmName);
        credentialsMatcher.setHashIterations(hashIterations);
        credentialsMatcher.setStoredCredentialsHexEncoded(storedCredentialsHexEncoded);
        return credentialsMatcher;
    }

    @Bean
    public List<Realm> realms(CredentialsMatcher credentialsMatcher, UserService simpleAccountService){
        return new ArrayList<Realm>(){{
            add(new UserRealm(credentialsMatcher, simpleAccountService));
        }};
    }

    @Bean
    public Object setSecurityManager(SecurityManager securityManager) throws Exception{
        MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        methodInvokingFactoryBean.setArguments(securityManager);
        return methodInvokingFactoryBean;
    }

//    @Bean
//    public Filter shiroFilter(SecurityManager securityManager) throws Exception{
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        shiroFilterFactoryBean.setSecurityManager(securityManager);
//        shiroFilterFactoryBean.setFilters(new HashMap<String, Filter>(){{put("user", userFilter());}});
//        shiroFilterFactoryBean.setFilterChainDefinitions("/** = user");
//        shiroFilterFactoryBean.setLoginUrl("/login");
//        return (Filter) shiroFilterFactoryBean.getObject();
//    }

//    @Bean
//    public Filter userFilter(){
//        return new UserFilter();
//    }
}
