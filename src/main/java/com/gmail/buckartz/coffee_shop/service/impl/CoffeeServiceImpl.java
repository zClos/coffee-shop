package com.gmail.buckartz.coffee_shop.service.impl;

import com.gmail.buckartz.coffee_shop.config.HibernateUtil;
import com.gmail.buckartz.coffee_shop.dao.CoffeeDao;
import com.gmail.buckartz.coffee_shop.dao.impl.CoffeeDaoImpl;
import com.gmail.buckartz.coffee_shop.domain.Coffee;
import com.gmail.buckartz.coffee_shop.service.CoffeeService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class CoffeeServiceImpl implements CoffeeService {
    private SessionFactory factory = HibernateUtil.getSessionFactory();
    private CoffeeDao coffeeDao = new CoffeeDaoImpl();

    private void beginOrJoinTransaction() {
        Session session = factory.getCurrentSession();
        if (session.getTransaction() == null) {
            session.getTransaction().begin();
        }
    }

    private void commitNotJoinedTransaction() {
        Session session = factory.getCurrentSession();
        if (!session.isJoinedToTransaction()) {
            session.getTransaction().commit();
        }
    }

    @Override
    public void save(Coffee coffee) {
        beginOrJoinTransaction();
        coffeeDao.save(coffee);
        commitNotJoinedTransaction();
    }

    @Override
    public List<Coffee> findAll() {
        beginOrJoinTransaction();
        List<Coffee> list = coffeeDao.findAll();
        commitNotJoinedTransaction();
        return list;
    }

    @Override
    public Coffee findById(Long id) {
        beginOrJoinTransaction();
        Coffee coffee = coffeeDao.findById(id);
        commitNotJoinedTransaction();
        return coffee;
    }
}
