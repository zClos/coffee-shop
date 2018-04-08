package com.gmail.buckartz.coffee_shop.dao;

import com.gmail.buckartz.coffee_shop.domain.CoffeeOrder;

import java.util.List;

public interface CoffeeOrderDao {
    CoffeeOrder findById(Long id);

    List<CoffeeOrder> findAll();

    void save(CoffeeOrder coffeeOrder);

    void delete(CoffeeOrder coffeeOrder);
}
