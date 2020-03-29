package com.myspringbt.demo.dataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration  // 交由Spring 的配置文件进行管理
//配置Spring要扫描的包：basePackages扫描的包的位置，sqlSessionFactoryRef要交给的session工厂
@MapperScan(basePackages = "demo.service.demo.dao", sqlSessionFactoryRef = "sqlSessionFactory")
public class DateConfig {

    @Bean(name = "dataSource") // 将数据源交由Spring管理
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource setDataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "sqlSessionFactory")   // 注册sql工厂
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("dataSource") DataSource masterDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/*.xml"));
        return sessionFactory.getObject();
    }

    @Bean(name = "transactionManager")  // 将sql工厂交由管理机构进行管理
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


}
