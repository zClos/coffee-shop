package com.gmail.buckartz.coffee_shop.dao;


import com.gmail.buckartz.coffee_shop.domain.Coffee;

import java.util.List;

public interface CoffeeDao {
    void save(Coffee coffee);

    List<Coffee> findAll();

    Coffee findById(Long id);
}
