package team.oha.laboa.config;

import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.config.ShiroConfiguration;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/11/30
 * @modified
 */
@Configuration
public class MyShiroConfig extends ShiroConfiguration {
    @Bean
    @Override
    protected SessionManager sessionManager() {
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        defaultWebSessionManager.setGlobalSessionTimeout(1800000);
        defaultWebSessionManager.setSessionDAO(sessionDAO());
        defaultWebSessionManager.setSessionFactory(sessionFactory());
        defaultWebSessionManager.setDeleteInvalidSessions(sessionManagerDeleteInvalidSessions);
        defaultWebSessionManager.setSessionIdCookie(sessionIdCookie());
        defaultWebSessionManager.setSessionIdCookieEnabled(true);
        return defaultWebSessionManager;
    }

    @Override
    protected SessionDAO sessionDAO() {
        return new EnterpriseCacheSessionDAO();
    }

    @Bean
    protected Cookie sessionIdCookie(){
        Cookie cookie = new SimpleCookie();
        cookie.setName("laboaId");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(1000 * 60 * 60 * 30);
        return cookie;
    }
}
