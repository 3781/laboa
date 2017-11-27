package team.oha.laboa.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

/**
 * <p>mybatis相关配置<p/>
 *
 * @author loser
 * @version 1.0
 * @date 2017/11/07
 * @modified
 */
@Configuration
@MapperScan(basePackages = "team.oha.laboa.dao")
@Import(DatasourceConfig.class)
public class MybatisConfig {
    @Bean
    public SqlSessionFactory sqlSessionFactory(TransactionAwareDataSourceProxy dataSource, @Value("classpath:mappers/*.xml") String mapperLocationsPattern) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        sessionFactory.setConfiguration(configuration);
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource[] mapperLocations = resourcePatternResolver.getResources(mapperLocationsPattern);
        sessionFactory.setMapperLocations(mapperLocations);
        sessionFactory.setDataSource(dataSource);
        return sessionFactory.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager(TransactionAwareDataSourceProxy dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
