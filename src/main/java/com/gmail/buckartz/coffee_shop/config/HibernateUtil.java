package com.gmail.buckartz.coffee_shop.config;

import com.gmail.buckartz.coffee_shop.domain.Coffee;
import com.gmail.buckartz.coffee_shop.domain.CoffeeOrder;
import com.gmail.buckartz.coffee_shop.domain.Delivery;
import com.gmail.buckartz.coffee_shop.util.Environment;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;

import java.util.HashMap;
import java.util.Map;


public final class HibernateUtil {
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    private HibernateUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                StandardServiceRegistryBuilder registryBuilder =
                        new StandardServiceRegistryBuilder();

                Map<String, String> settings = new HashMap<>();
                settings.put(AvailableSettings.DRIVER, Environment.getProperty("db.driver"));
                settings.put(AvailableSettings.URL, Environment.getProperty("db.url"));
                settings.put(AvailableSettings.USER, Environment.getProperty("db.user"));
                settings.put(AvailableSettings.DIALECT, Environment.getProperty("hibernate.dialect"));
                settings.put(AvailableSettings.USE_REFLECTION_OPTIMIZER, Environment.getProperty("hibernate.bytecode.use_reflection_optimizer"));
                settings.put(AvailableSettings.SHOW_SQL, Environment.getProperty("hibernate.show_sql"));
                settings.put(AvailableSettings.FORMAT_SQL, Environment.getProperty("hibernate.format_sql"));
                settings.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, Environment.getProperty("hibernate.current_session_context_class"));
                settings.put(AvailableSettings.HBM2DDL_AUTO, Environment.getProperty("hibernate.hbm2ddl.auto"));
                settings.put(AvailableSettings.ISOLATION, Environment.getProperty("hibernate.connection.isolation"));

                registryBuilder.applySettings(settings);
                registry = registryBuilder.build();
                MetadataSources sources = new MetadataSources(registry)
                        .addAnnotatedClass(Coffee.class)
                        .addAnnotatedClass(CoffeeOrder.class)
                        .addAnnotatedClass(Delivery.class);

                sessionFactory = sources.buildMetadata().buildSessionFactory();
            } catch (Exception e) {
                if (registry != null) {
                    e.printStackTrace();
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;
    }
}