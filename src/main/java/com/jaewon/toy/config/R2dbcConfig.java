package com.jaewon.toy.config;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@EnableR2dbcAuditing
@EnableR2dbcRepositories
@Configuration
public class R2dbcConfig {
    @Value("${spring.data.r2dbc.postgresql.driver}")
    private String driver;
    @Value("${spring.data.r2dbc.postgresql.host}")
    private String host;
    @Value("${spring.data.r2dbc.postgresql.port}")
    private int port;
    @Value("${spring.data.r2dbc.postgresql.user}")
    private String user;
    @Value("${spring.data.r2dbc.postgresql.password}")
    private String password;
    @Value("${spring.data.r2dbc.postgresql.database}")
    private String database;

    @Bean
    public ConnectionFactory connectionFactory() {
        return ConnectionFactories.get(ConnectionFactoryOptions.builder()
                .option(DRIVER, driver)
                .option(HOST, host)
                .option(PORT, port)
                .option(USER, user)
                .option(PASSWORD, password)
                .option(DATABASE, database)
                .build());
    }
}
