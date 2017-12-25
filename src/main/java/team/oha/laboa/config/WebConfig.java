package team.oha.laboa.config;

import com.google.common.base.Charsets;
import org.springframework.lang.Nullable;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.util.IntrospectorCleanupListener;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EnumSet;

/**
 * <p>web配置</p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/11/7
 * @modified
 */
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    private Filter delegatingFilterProxy(){
        DelegatingFilterProxy filter = new DelegatingFilterProxy();
        filter.setTargetFilterLifecycle(true);
        filter.setTargetBeanName("shiroFilterFactoryBean");
        return filter;
    }

    @Nullable
    @Override
    protected Filter[] getServletFilters() {
        return new Filter[] {delegatingFilterProxy()};
    }

    public void registerCharacterEncodingFilter(ServletContext servletContext){
        Filter characterEncodingFilter = new CharacterEncodingFilter(Charsets.UTF_8.toString(), true);
        FilterRegistration filterRegistration = servletContext.addFilter(characterEncodingFilter.getClass().getSimpleName(), characterEncodingFilter);
        filterRegistration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD), false, "/*");
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        registerIntrospectorCleanupListener(servletContext);
        registerCharacterEncodingFilter(servletContext);
        super.onStartup(servletContext);
        initIndexHtml(servletContext);
        registerPageServlet(servletContext);
        registerStaticResourceServlet(servletContext);
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
        return new String[]{"/api/*"};
    }


    private static final String INDEX_LOCATION = "/index.html";
    private static final String DEFAULT_SERVLET_NAME = "default";
    private byte[] indexHtml;

    protected void initIndexHtml(ServletContext servletContext){
        ServletContextResource resource = new ServletContextResource(servletContext, INDEX_LOCATION);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), "UTF-8"));
            indexHtml = reader.readLine().getBytes("UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void registerPageServlet(ServletContext servletContext){
        servletContext.addServlet("pageServlet", new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                resp.getOutputStream().write(indexHtml);
            }
        }).addMapping("/");
    }

    protected void registerStaticResourceServlet(ServletContext servletContext){
        servletContext.getServletRegistrations().get(DEFAULT_SERVLET_NAME).addMapping("/static/*");
    }
}
