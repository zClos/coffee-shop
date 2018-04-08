package com.gmail.buckartz.coffee_shop.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "coffee")
@Getter
@Setter
@EqualsAndHashCode(exclude = "coffeeOrders")
@ToString(exclude = "coffeeOrders")
public class Coffee {
    @Id
    @SequenceGenerator(name = "coffee_seq", sequenceName = "coffee_seq", allocationSize = 0)
    @GeneratedValue(generator = "coffee_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Integer price;

    @OneToMany(mappedBy = "coffee")
    private Set<CoffeeOrder> coffeeOrders = new HashSet<>();

    public static CoffeeBuilder builder() {
        return new CoffeeBuilder();
    }

    public static class CoffeeBuilder {
        private Coffee coffee = new Coffee();

        private CoffeeBuilder() {
        }

        public CoffeeBuilder name(String name) {
            coffee.setName(name);
            return this;
        }

        public CoffeeBuilder price(Integer price) {
            coffee.setPrice(price);
            return this;
        }

        public CoffeeBuilder coffeeOrders(CoffeeOrder coffeeOrder) {
            coffee.getCoffeeOrders().add(coffeeOrder);
            coffeeOrder.setCoffee(coffee);
            return this;
        }

        public Coffee build() {
            return coffee;
        }
    }
}
