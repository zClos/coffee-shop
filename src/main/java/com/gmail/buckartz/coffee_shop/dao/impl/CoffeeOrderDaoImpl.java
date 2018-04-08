package com.gmail.buckartz.coffee_shop.dao.impl;

import com.gmail.buckartz.coffee_shop.config.HibernateUtil;
import com.gmail.buckartz.coffee_shop.dao.CoffeeOrderDao;
import com.gmail.buckartz.coffee_shop.domain.CoffeeOrder;
import org.hibernate.SessionFactory;

import java.util.List;

public class CoffeeOrderDaoImpl implements CoffeeOrderDao {
    private SessionFactory factory = HibernateUtil.getSessionFactory();

    @Override
    public CoffeeOrder findById(Long id) {
        return factory.getCurrentSession().get(CoffeeOrder.class, id);
    }

    @Override
    public List<CoffeeOrder> findAll() {
        return factory.getCurrentSession().createQuery("SELECT co FROM CoffeeOrder co", CoffeeOrder.class).list();
    }

    @Override
    public void save(CoffeeOrder coffeeOrder) {
        factory.getCurrentSession().saveOrUpdate(coffeeOrder);
    }

    @Override
    public void delete(CoffeeOrder coffeeOrder) {
        factory.getCurrentSession().remove(coffeeOrder);
    }
}
