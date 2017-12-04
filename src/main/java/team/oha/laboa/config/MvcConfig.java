package team.oha.laboa.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.apache.shiro.spring.config.ShiroAnnotationProcessorConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

/**
 * <p>springmvc配置</p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/11/7
 * @modified
 */

@Configuration
@EnableWebMvc
@ComponentScan("team.oha.laboa.controller")
@Import(ShiroAnnotationProcessorConfiguration.class)
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        Iterator<HttpMessageConverter<?>> iterator = converters.iterator();
        while(iterator.hasNext()){
            HttpMessageConverter httpMessageConverter = iterator.next();
            if( httpMessageConverter instanceof MappingJackson2HttpMessageConverter ){
                MappingJackson2HttpMessageConverter converter = (MappingJackson2HttpMessageConverter) httpMessageConverter;
                ObjectMapper objectMapper = converter.getObjectMapper();
                JavaTimeModule javaTimeModule = new JavaTimeModule();
                javaTimeModule.addDeserializer(LocalDateTime.class,
                        new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                javaTimeModule.addSerializer(LocalDateTime.class,
                        new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                javaTimeModule.addSerializer(LocalDate.class,
                        new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                javaTimeModule.addDeserializer(LocalDate.class,
                        new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                objectMapper.registerModule(javaTimeModule);
                objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
                break;
            }
        }
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/static/");
    }
}
