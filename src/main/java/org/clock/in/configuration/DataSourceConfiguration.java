package org.clock.in.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.sql.SQLException;

@Configuration
@PropertySource("classpath:config.properties")
public class DataSourceConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public JdbcTemplate jdbcTemplate() throws SQLException {

        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriver(new com.mysql.jdbc.Driver());

        String url = "jdbc:mysql://" + env.getProperty("database.host") + "/" + env.getProperty("database.schema");
        dataSource.setUrl(url);

        dataSource.setUsername(env.getProperty("database.user"));
        dataSource.setPassword(env.getProperty("database.password"));

        return new JdbcTemplate(dataSource);
    }
}
