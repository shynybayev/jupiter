package kz.example.jupiter.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.*;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.FileSystemResourceAccessor;
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

    private String getUrl() {
        return "jdbc:mysql://localhost:3306/jupiter";
    }

    private String getPassword() {
        return "rootdb";
    }

    private String getUsername() {
        return "rootdb";
    }

    /**
     * происходит connection, создается пул
     */
    @PostConstruct
    private void createPool() {

        applyLiquibase();

        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(getUrl());
        config.setUsername(getUsername());
        config.setPassword(getPassword());

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        dataSource = new HikariDataSource(config);
    }

    private void applyLiquibase() {

        try(Connection con= DriverManager.getConnection(getUrl(),getUsername(),getPassword())) {

                Class.forName("com.mysql.jdbc.Driver");

                Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(con));
                Liquibase liquibase = new Liquibase("liquibase/main-change-log.xml", new ClassLoaderResourceAccessor(), database);
                liquibase.update((String) null);

        } catch(Exception e) {
            System.err.println(e);
        }
    }

    @Bean
    public DataSource getDataSource() {
        return dataSource;
    }

    @PreDestroy
    private void destroyPool() {
        dataSource.close();
    }

//    public static void main(String[] args) {
//        DBConnect dbConnect = new DBConnect();
//        dbConnect.applyLiquibase();
//    }
}
