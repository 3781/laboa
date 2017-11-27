package team.oha.laboa.config;

import org.springframework.lang.Nullable;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.util.IntrospectorCleanupListener;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * <p>web配置</p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/11/7
 * @modified
 */
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    private Filter characterEncodingFilter(){
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }

    @Nullable
    @Override
    protected Filter[] getServletFilters() {
        return new Filter[] {characterEncodingFilter()};
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        registerIntrospectorCleanupListener(servletContext);
//        registerShiroFilter(servletContext);
        super.onStartup(servletContext);
    }

    public void  registerIntrospectorCleanupListener(ServletContext servletContext){
        servletContext.addListener(new IntrospectorCleanupListener());
    }

    @Nullable
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    @Nullable
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{MvcConfig.class};
    }

    @Nullable
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }


//    public void registerShiroFilter(ServletContext servletContext){
//        FilterRegistration shiroFilter = servletContext.addFilter("shiroFilter", DelegatingFilterProxy.class);
//        shiroFilter.setInitParameter("targetFilterLifecycle", "true");
//        shiroFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/api/*");
//    }
}
