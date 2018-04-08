package com.gmail.buckartz.coffee_shop.service.impl;

import com.gmail.buckartz.coffee_shop.config.TestConfig;
import com.gmail.buckartz.coffee_shop.domain.Coffee;
import com.gmail.buckartz.coffee_shop.service.CoffeeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CoffeeServiceTest extends TestConfig {
    private CoffeeService coffeeService;

    @Before
    public void beforeThis() {
        coffeeService = new CoffeeServiceImpl();
    }

    @Test
    public void testSave() throws Exception {
        beginTestTransaction();
        Coffee coffee = Coffee.builder().name("Nescafe").price(8).build();
        coffeeService.save(coffee);
        Assert.assertNotNull(coffee.getId());
        rollbackTestTransaction();
    }

    @Test
    public void testFindAll() throws Exception {
        beginTestTransaction();
        Coffee coffee1 = Coffee.builder().name("Nescafe").price(8).build();
        coffeeService.save(coffee1);
        Coffee coffee2 = Coffee.builder().name("Nescafe").price(8).build();
        coffeeService.save(coffee2);

        List<Coffee> result = coffeeService.findAll();
        Assert.assertEquals(2, result.size());
        rollbackTestTransaction();
    }

    @Test
    public void testFindById() throws Exception {
        beginTestTransaction();
        Coffee coffee = Coffee.builder().name("Nescafe").price(8).build();
        coffeeService.save(coffee);
        flushAndClearSession();

        Coffee result = coffeeService.findById(coffee.getId());
        Assert.assertEquals(coffee, result);
        rollbackTestTransaction();
    }
}