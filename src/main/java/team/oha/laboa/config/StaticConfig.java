package team.oha.laboa.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.ServletContextResource;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <p>web配置</p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/11/7
 * @modified
 */
public class StaticConfig implements WebApplicationInitializer {

    private static final String INDEX_LOCATION = "/index.html";
    private static final String DEFAULT_SERVLET_NAME = "default";
    private byte[] indexHtml;

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        initIndexHtml(servletContext);
        registerPageServlet(servletContext);
        registerStaticResourceServlet(servletContext);
    }

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
