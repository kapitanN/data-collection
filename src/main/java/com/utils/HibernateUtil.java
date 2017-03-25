package com.utils;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by nikita on 24.03.2017.
 */
public class HibernateUtil {
    private static final Logger LOG = Logger.getLogger(HibernateUtil.class);

    private static final String CONFIGURATION_LOADED = "Hibernate Configuration has been loaded.";
    private static final String REGISTRY_LOADED = "Hibernate Annotation Service Registry has been created.";
    private static final String CREATION_FAILED = "Initial Session Factory creation failed.";

    private static SessionFactory sessionFactory = buildSessionFactory();

    protected static SessionFactory buildSessionFactory() {
        // A SessionFactory is set up once for an application!
        try {
            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                    .configure( "hibernate.cfg.xml" )
                    .build();

            Metadata metadata = new MetadataSources( standardRegistry )
                    .getMetadataBuilder()
                    .build();

            sessionFactory = metadata.getSessionFactoryBuilder().build();
        }
        catch (Exception ex) {
            LOG.error(CREATION_FAILED);
            LOG.error(ex);
            throw new ExceptionInInitializerError(ex);
        }
        return sessionFactory;
    }


    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
}
