package team.oha.laboa.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.apache.shiro.spring.config.ShiroAnnotationProcessorConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import team.oha.laboa.formatter.LocalDateFormatter;
import team.oha.laboa.formatter.LocalDateTimeFormatter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.fasterxml.jackson.databind.MapperFeature.DEFAULT_VIEW_INCLUSION;

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

    private final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private final String DATE_PATTERN = "yyyy-MM-dd";

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new LocalDateFormatter());
        registry.addFormatter(new LocalDateTimeFormatter());
    }

    @Bean
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        return resolver;
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        Iterator<HttpMessageConverter<?>> iterator = converters.iterator();
        while(iterator.hasNext()){
            HttpMessageConverter httpMessageConverter = iterator.next();
            if( httpMessageConverter instanceof MappingJackson2HttpMessageConverter ){
                MappingJackson2HttpMessageConverter converter = (MappingJackson2HttpMessageConverter) httpMessageConverter;
                ObjectMapper objectMapper = new ObjectMapper();

                JavaTimeModule javaTimeModule = new JavaTimeModule();
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter));
                javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter));
                javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter));
                objectMapper.registerModule(javaTimeModule);
                objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                objectMapper.configure(DEFAULT_VIEW_INCLUSION, false);
                converter.setSupportedMediaTypes(new ArrayList<MediaType>(){{
                    add(MediaType.APPLICATION_JSON);
                    add(MediaType.APPLICATION_JSON_UTF8);
                    add(MediaType.TEXT_HTML);
                    add(MediaType.APPLICATION_FORM_URLENCODED);
                }});
                converter.setObjectMapper(objectMapper);
                break;
            }
        }
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/static/");
    }
}
