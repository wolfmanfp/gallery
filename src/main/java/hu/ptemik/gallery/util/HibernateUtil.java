package hu.ptemik.gallery.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

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
