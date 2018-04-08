package com.gmail.buckartz.coffee_shop.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "coffee")
@Getter
@Setter
@EqualsAndHashCode
public class Coffee {
    @Id
    @SequenceGenerator(name = "coffee_seq", sequenceName = "coffee_seq", allocationSize = 0)
    @GeneratedValue(generator = "coffee_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Integer price;

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

        public Coffee build() {
            return coffee;
        }
    }
}
