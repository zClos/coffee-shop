package com.gmail.buckartz.coffee_shop.service.impl;

import com.gmail.buckartz.coffee_shop.config.TestConfig;
import com.gmail.buckartz.coffee_shop.domain.Coffee;
import com.gmail.buckartz.coffee_shop.domain.CoffeeOrder;
import com.gmail.buckartz.coffee_shop.domain.Delivery;
import com.gmail.buckartz.coffee_shop.service.CoffeeOrderService;
import com.gmail.buckartz.coffee_shop.service.CoffeeService;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalTime.of;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class CoffeeOrderServiceImplTest extends TestConfig {
    private CoffeeOrderService coffeeOrderService;
    private CoffeeService coffeeService;

    @Before
    public void beforeThis() {
        coffeeOrderService = new CoffeeOrderServiceImpl();
        coffeeService = new CoffeeServiceImpl();
    }

    @Test
    public void testFindById() throws Exception {
        beginTestTransaction();
        Coffee coffee = Coffee.builder()
                .name("Lavazza")
                .price(10)
                .build();
        coffeeService.save(coffee);
        CoffeeOrder coffeeOrder = CoffeeOrder.builder()
                .coffee(coffee)
                .delivery(Delivery.builder()
                        .date(LocalDate.of(2018, 5, 10))
                        .timeFrom(of(10, 30))
                        .timeTo(of(11, 30))
                        .price(2)
                        .build())
                .weight(2)
                .totalPrice(22)
                .build();
        coffeeOrderService.save(coffeeOrder);
        flushAndClearSession();
        CoffeeOrder result = coffeeOrderService.findById(coffeeOrder.getId());

        assertEquals(coffeeOrder.getId(), result.getId());
        assertEquals(coffeeOrder.getCoffee().getId(), result.getCoffee().getId());
        assertEquals(coffeeOrder.getDelivery().getId(), result.getDelivery().getId());
        rollbackTestTransaction();
    }

    @Test
    public void testFindAll() throws Exception {
        beginTestTransaction();
        Coffee coffee1 = Coffee.builder()
                .name("Lavazza")
                .price(10)
                .build();
        coffeeService.save(coffee1);
        CoffeeOrder coffeeOrder1 = CoffeeOrder.builder()
                .coffee(coffee1)
                .delivery(Delivery.builder()
                        .date(LocalDate.of(2018, 5, 10))
                        .timeFrom(of(10, 30))
                        .timeTo(of(11, 30))
                        .price(2)
                        .build())
                .weight(2)
                .totalPrice(22)
                .build();
        coffeeOrderService.save(coffeeOrder1);
        Coffee coffee2 = Coffee.builder()
                .name("Lavazza")
                .price(10)
                .build();
        coffeeService.save(coffee2);
        CoffeeOrder coffeeOrder2 = CoffeeOrder.builder()
                .coffee(coffee2)
                .delivery(Delivery.builder()
                        .date(LocalDate.of(2018, 5, 10))
                        .timeFrom(of(10, 30))
                        .timeTo(of(11, 30))
                        .price(2)
                        .build())
                .weight(2)
                .totalPrice(22)
                .build();
        coffeeOrderService.save(coffeeOrder2);

        List<CoffeeOrder> result = coffeeOrderService.findAll();
        assertTrue(result.contains(coffeeOrder1));
        assertTrue(result.contains(coffeeOrder2));
        rollbackTestTransaction();
    }

    @Test
    public void testSave() throws Exception {
        beginTestTransaction();
        Coffee coffee = Coffee.builder()
                .name("Lavazza")
                .price(10)
                .build();
        coffeeService.save(coffee);
        CoffeeOrder coffeeOrder = CoffeeOrder.builder()
                .coffee(coffee)
                .delivery(Delivery.builder()
                        .date(LocalDate.of(2018, 5, 10))
                        .timeFrom(of(10, 30))
                        .timeTo(of(11, 30))
                        .price(2)
                        .build())
                .weight(2)
                .totalPrice(22)
                .build();
        coffeeOrderService.save(coffeeOrder);

        assertNotNull(coffee.getId());
        rollbackTestTransaction();
    }

    @Test
    public void testDelete() throws Exception {
        beginTestTransaction();
        Coffee coffee = Coffee.builder()
                .name("Lavazza")
                .price(10)
                .build();
        coffeeService.save(coffee);
        CoffeeOrder coffeeOrder = CoffeeOrder.builder()
                .coffee(coffee)
                .delivery(Delivery.builder()
                        .date(LocalDate.of(2018, 5, 10))
                        .timeFrom(of(10, 30))
                        .timeTo(of(11, 30))
                        .price(2)
                        .build())
                .weight(2)
                .totalPrice(22)
                .build();
        coffeeOrderService.save(coffeeOrder);
        flushAndClearSession();

        coffeeOrderService.delete(coffeeOrder);
        flushAndClearSession();

        assertNull(coffeeOrderService.findById(coffeeOrder.getId()));
        rollbackTestTransaction();
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme