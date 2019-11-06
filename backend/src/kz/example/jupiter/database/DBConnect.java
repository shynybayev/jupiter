package kz.example.jupiter.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;

/**
 * Pool connect to DB
 */
@Component
public class DBConnect {
    private HikariDataSource dataSource;

    @PostConstruct
    private void createPool() {

        HikariConfig config = new HikariConfig();

        config.setJdbcUrl("jdbc:mysql://localhost:3306/jupiter");
        config.setUsername("rootdb");
        config.setPassword("rootdb");

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        dataSource = new HikariDataSource(config);
    }

    @Bean
    public DataSource getDataSource() {
        return dataSource;
    }

    @PreDestroy
    private void destroyPool() {
        dataSource.close();
    }
}
