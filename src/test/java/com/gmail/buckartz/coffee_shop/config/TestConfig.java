package com.gmail.buckartz.coffee_shop.config;

import org.hibernate.SessionFactory;
import org.junit.Before;

public class TestConfig {
    protected SessionFactory factory;

    @Before
    public void before() {
        factory = HibernateUtil.getSessionFactory();
    }

    protected void beginTestTransaction() {
        factory.getCurrentSession().beginTransaction();
    }

    protected void rollbackTestTransaction() {
        factory.getCurrentSession().getTransaction().rollback();
    }

    protected void flushAndClearSession() {
        factory.getCurrentSession().flush();
        factory.getCurrentSession().clear();
    }
}
