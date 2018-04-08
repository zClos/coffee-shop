package com.gmail.buckartz.coffee_shop.service;

import com.gmail.buckartz.coffee_shop.domain.Coffee;

import java.util.List;

public interface CoffeeService {
    void save(Coffee coffee);

    List<Coffee> findAll();

    Coffee findById(Long id);
}
