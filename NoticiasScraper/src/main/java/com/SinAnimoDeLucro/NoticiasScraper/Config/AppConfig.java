package com.SinAnimoDeLucro.NoticiasScraper.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
    dataSource.setUrl("${DB_URL}");
    dataSource.setUsername("${DB_USER}");
    dataSource.setPassword("${DB_PASSWORD}");
    return dataSource;
  }
}
