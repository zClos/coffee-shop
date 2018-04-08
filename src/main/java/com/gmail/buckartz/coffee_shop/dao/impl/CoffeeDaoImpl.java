package com.gmail.buckartz.coffee_shop.dao.impl;

import com.gmail.buckartz.coffee_shop.config.HibernateUtil;
import com.gmail.buckartz.coffee_shop.dao.CoffeeDao;
import com.gmail.buckartz.coffee_shop.domain.Coffee;
import org.hibernate.SessionFactory;

import java.util.List;

public class CoffeeDaoImpl implements CoffeeDao {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public void save(Coffee coffee) {
        sessionFactory.getCurrentSession().saveOrUpdate(coffee);
    }

    @Override
    public List<Coffee> findAll() {
        return sessionFactory.getCurrentSession().createQuery("SELECT c FROM Coffee c", Coffee.class).list();
    }

    @Override
    public Coffee findById(Long id) {
        return sessionFactory.getCurrentSession().find(Coffee.class, id);
    }
}
