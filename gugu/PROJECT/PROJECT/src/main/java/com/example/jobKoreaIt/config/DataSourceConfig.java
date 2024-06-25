package com.example.jobKoreaIt.config;


import com.example.jobKoreaIt.properties.DBCONN;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

    @Bean
    public HikariDataSource dataSource(){
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(DBCONN.URL);
        dataSource.setUsername(DBCONN.ID);
        dataSource.setPassword(DBCONN.PW);

        return dataSource;
    }

}
