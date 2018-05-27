package com.gm.api.config;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by xxhh on 2017/6/22.
 */

@Configuration
@MapperScan(annotationClass = Mapper.class, basePackages = "com.gm.api.dao",
        sqlSessionFactoryRef = DBConfiguration.SQL_SESSION_FACTORY)
public class DBConfiguration extends AbstractDBConfiguration {

    static final String SQL_SESSION_FACTORY = "gmSqlSessionFactory";

    @Value("${mysql.gm.database}")
    private String database;

    @Value("${mysql.gm.host}")
    private String host;

    @Override
    protected String getDatabase() {
        return database;
    }

    @Override
    protected String getHost() {
        return host;
    }

    @Override
    @Bean("gmDataSource")
    public DataSource dataSource() throws SQLException {
        return super.dataSource();
    }

    @Override
    @Bean(SQL_SESSION_FACTORY)
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        return super.sqlSessionFactory();
    }

    @Override
    @Bean("gmDataSourceTransactionManager")
    public DataSourceTransactionManager transactionManager() throws SQLException {
        return super.transactionManager();
    }
}

