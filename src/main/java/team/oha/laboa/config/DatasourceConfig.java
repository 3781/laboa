package team.oha.laboa.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * <p>数据源配置<p/>
 *
 * @author loser
 * @version 1.0
 * @date 2017/11/07
 * @modified
 */
@Configuration
public class DatasourceConfig {

    @Bean(initMethod = "init", destroyMethod = "close")
    DruidDataSource dataSource(
            @Value("${connection.url}") String url,
            @Value("${connection.username}") String username,
            @Value("${connection.password}") String password,
            @Value("${druid.connectionProperties.config.decrypt}") String configDecrypt,
            @Value("${druid.connectionProperties.config.decrypt.key}") String configDecryptKey,
            @Value("${connection.driver_class}") String driverClassName,
            @Value("${druid.initialSize}") Integer initialSize,
            @Value("${druid.minIdle}") Integer minIdle,
            @Value("${druid.maxActive}") Integer maxActive,
            @Value("${druid.maxWait}") Long maxWait,
            @Value("${druid.timeBetweenEvictionRunsMillis}") Long timeBetweenEvictionRunsMillis,
            @Value("${druid.minEvictableIdleTimeMillis}") Long minEvictableIdleTimeMillis,
            @Value("${druid.validationQuery}") String validationQuery,
            @Value("${druid.testOnBorrow}") Boolean testOnBorrow,
            @Value("${druid.testOnReturn}") Boolean testOnReturn,
            @Value("${druid.testWhileIdle}") Boolean testWhileIdle,
            @Value("${druid.removeAbandoned}") Boolean removeAbandoned,
            @Value("${druid.removeAbandonedTimeout}") Integer removeAbandonedTimeout,
            @Value("${druid.logAbandoned}") Boolean logAbandoned,
            @Value("${druid.filters}") String filters
    ) throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        Properties connectionProperties = new Properties();
        connectionProperties.setProperty("config.decrypt",configDecrypt);
        connectionProperties.setProperty("config.decrypt.key",configDecryptKey);
        druidDataSource.setConnectProperties(connectionProperties);
        druidDataSource.setDriverClassName(driverClassName);
        druidDataSource.setInitialSize(initialSize);
        druidDataSource.setMinIdle(minIdle);
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setMaxWait(maxWait);
        druidDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        druidDataSource.setValidationQuery(validationQuery);
        druidDataSource.setTestOnBorrow(testOnBorrow);
        druidDataSource.setTestOnReturn(testOnReturn);
        druidDataSource.setTestWhileIdle(testWhileIdle);
        druidDataSource.setRemoveAbandoned(removeAbandoned);
        druidDataSource.setRemoveAbandonedTimeout(removeAbandonedTimeout);
        druidDataSource.setLogAbandoned(logAbandoned);
        druidDataSource.setFilters(filters);

        return druidDataSource;
    }

    @Bean
    public TransactionAwareDataSourceProxy dataSourceProxy(DataSource dataSource){
        return new TransactionAwareDataSourceProxy(dataSource);
    }
}
