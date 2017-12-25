package team.oha.laboa.config;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>web配置</p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/11/7
 * @modified
 */
public class StaticConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    public static final String DEFAULT_SERVLET_NAME = "static";

    @Override
    protected String getServletName() {
        return DEFAULT_SERVLET_NAME;
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        registerStaticServlet(servletContext);
        super.onStartup(servletContext);
    }

    @Nullable
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Nullable
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{ResourceConfig.class};
    }

    @Nullable
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/static/*"};
    }


    private static final String DEFAULT_JSP_SERVLET_NAME = "jsp";
    private static final String INDEX_LOCATION = "/WEB-INF/index.html";

    public void registerStaticServlet(ServletContext servletContext){
        servletContext.addServlet("pageServlet", new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                req.getRequestDispatcher(INDEX_LOCATION).forward(req, resp);
            }
        }).addMapping("/");
        servletContext.getServletRegistrations().get(DEFAULT_JSP_SERVLET_NAME).addMapping(INDEX_LOCATION);
    }
}
