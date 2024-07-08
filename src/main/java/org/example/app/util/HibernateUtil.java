package org.example.app.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import org.example.app.dao.model.Client;
import org.example.app.dao.model.Ticket;
import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Getter
public class HibernateUtil {
    private final SessionFactory sessionFactory;
    private static final HikariDataSource dataSource;

    static {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5437/MyDatabase");
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("password");
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setConnectionTimeout(30000);

        dataSource = new HikariDataSource(hikariConfig);

        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("db/migrations")
                .load();
        flyway.migrate();
    }

    private HibernateUtil() {
        sessionFactory = new Configuration()
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Ticket.class)
                .buildSessionFactory();
    }

    private static final class InstanceHolder {
        private static final HibernateUtil instance = new HibernateUtil();
    }

    public static HibernateUtil getInstance() {
        return InstanceHolder.instance;
    }

    public void close() {
        sessionFactory.close();
    }
}
