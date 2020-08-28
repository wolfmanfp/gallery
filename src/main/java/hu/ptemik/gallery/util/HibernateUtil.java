package hu.ptemik.gallery.util;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

import java.sql.Connection;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author János
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                // Create the SessionFactory from standard (hibernate.cfg.xml)
                // config file.
                StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
                MetadataSources sources = new MetadataSources(registry);

                // Get database connection
                Connection con = sources.getServiceRegistry().getService(ConnectionProvider.class).getConnection();
                JdbcConnection jdbcCon = new JdbcConnection(con);

                // Initialize Liquibase and run the update
                Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(jdbcCon);
                Liquibase liquibase = new Liquibase("changelog.xml", new ClassLoaderResourceAccessor(), database);
                liquibase.update("gallery");

                // Create Hibernate SessionFactory
                Metadata metadata = sources.getMetadataBuilder().build();
                sessionFactory = metadata.getSessionFactoryBuilder().build();
            } catch (Throwable ex) {
                // Log the exception.
                System.out.println("Initial SessionFactory creation failed." + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }
        return sessionFactory;
    }
}
