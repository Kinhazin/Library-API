package io.github.kinhazin.libraryapi.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
@Getter
@Setter
public class DatabaseConfiguration {

    private String url;
    private String username;
    private String password;
    private String driverClassName;


  //  @Bean
    public DataSource dataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(url);
        ds.setDriverClassName(driverClassName);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }

    @Bean
    public DataSource hikariDataSource(){
        HikariConfig config = new HikariConfig();
        config.setUsername(username);
        config.setJdbcUrl(url);
        config.setPassword(password);
        config.setDriverClassName(driverClassName);

        config.setMaximumPoolSize(10); //maximo de conexões liberdades
        config.setMinimumIdle(1); //tamanho inicial do pool
        config.setPoolName("library-db-pool");
        config.setMaxLifetime(600000L); //10 minutos
        config.setConnectionTimeout(100000L);
        config.setConnectionTestQuery("select 1");

        return new HikariDataSource(config);
    }


}
