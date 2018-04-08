package com.gmail.buckartz.coffee_shop.service.impl;

import com.gmail.buckartz.coffee_shop.config.HibernateUtil;
import com.gmail.buckartz.coffee_shop.dao.CoffeeDao;
import com.gmail.buckartz.coffee_shop.dao.CoffeeOrderDao;
import com.gmail.buckartz.coffee_shop.dao.impl.CoffeeDaoImpl;
import com.gmail.buckartz.coffee_shop.dao.impl.CoffeeOrderDaoImpl;
import com.gmail.buckartz.coffee_shop.domain.CoffeeOrder;
import com.gmail.buckartz.coffee_shop.service.CoffeeOrderService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class CoffeeOrderServiceImpl implements CoffeeOrderService {
    private SessionFactory factory = HibernateUtil.getSessionFactory();
    private CoffeeOrderDao orderDao = new CoffeeOrderDaoImpl();

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
    public CoffeeOrder findById(Long id) {
        beginOrJoinTransaction();
        CoffeeOrder coffeeOrder = orderDao.findById(id);
        commitNotJoinedTransaction();
        return coffeeOrder;
    }

    @Override
    public List<CoffeeOrder> findAll() {
        beginOrJoinTransaction();
        List<CoffeeOrder> list = orderDao.findAll();
        commitNotJoinedTransaction();
        return list;
    }

    @Override
    public void save(CoffeeOrder coffeeOrder) {
        beginOrJoinTransaction();
        orderDao.save(coffeeOrder);
        commitNotJoinedTransaction();
    }

    @Override
    public void delete(CoffeeOrder coffeeOrder) {
        beginOrJoinTransaction();
        orderDao.delete(coffeeOrder);
        commitNotJoinedTransaction();
    }
}
