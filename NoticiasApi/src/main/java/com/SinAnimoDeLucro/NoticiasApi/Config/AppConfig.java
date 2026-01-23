package com.SinAnimoDeLucro.NoticiasApi.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class AppConfig {


        @Value("${DB_URL}")
        private String dbUrl;

        @Value("${DB_USER}")
        private String dbUser;

        @Value("${DB_PASSWORD}")
        private String dbPassword;

        @Bean
        public DataSource dataSource() {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dataSource.setUrl(dbUrl);
            dataSource.setUsername(dbUser);
            dataSource.setPassword(dbPassword);
            return dataSource;
        }
}
