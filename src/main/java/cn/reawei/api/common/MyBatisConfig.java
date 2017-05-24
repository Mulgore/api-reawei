package cn.reawei.api.common;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * springboot集成mybatis的基本入口
 * 1）创建数据源
 * 2）创建SqlSessionFactory
 */

@Configuration    //该注解类似于spring配置文件
@EnableTransactionManagement
@PropertySource(value = {"classpath:properties/jdbc.properties"})
public class MyBatisConfig {

    @Autowired
    private Environment env;

    /**
     * 创建数据源
     *
     * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
     */
    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(env.getProperty("source.driverClassName").trim());
        dataSource.setUrl(env.getProperty("source.url").trim());
        dataSource.setUsername(env.getProperty("source.username").trim());
        dataSource.setPassword(env.getProperty("source.password").trim());
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws Exception {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }
}
