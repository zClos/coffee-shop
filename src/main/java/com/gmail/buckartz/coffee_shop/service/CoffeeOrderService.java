package com.gmail.buckartz.coffee_shop.service;

import com.gmail.buckartz.coffee_shop.domain.CoffeeOrder;

import java.util.List;

public interface CoffeeOrderService {
    CoffeeOrder findById(Long id);

    List<CoffeeOrder> findAll();

    void save(CoffeeOrder coffeeOrder);

    void delete(CoffeeOrder coffeeOrder);
}
