package com.gmail.buckartz.coffee_shop.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "coffee_order")
@Getter
@Setter
@EqualsAndHashCode(exclude = {"coffee", "delivery"})
@ToString(exclude = {"coffee", "delivery"})
public class CoffeeOrder {
    @Id
    @SequenceGenerator(name = "coffee_order_seq", sequenceName = "coffee_order_seq", allocationSize = 0)
    @GeneratedValue(generator = "coffee_order_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "total_price")
    private Integer totalPrice;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, optional = false)
    @JoinColumn(name = "coffee_id", referencedColumnName = "id")
    private Coffee coffee;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "delivery_id", referencedColumnName = "id")
    private Delivery delivery;

    public static CoffeeOrderBuilder builder() {
        return new CoffeeOrderBuilder();
    }

    public static class CoffeeOrderBuilder {
        private CoffeeOrder coffeeOrder = new CoffeeOrder();

        private CoffeeOrderBuilder() {
        }

        public CoffeeOrderBuilder weight(Integer weight) {
            coffeeOrder.setWeight(weight);
            return this;
        }

        public CoffeeOrderBuilder totalPrice(Integer totalPrice) {
            coffeeOrder.setTotalPrice(totalPrice);
            return this;
        }

        public CoffeeOrderBuilder coffee(Coffee coffee) {
            coffeeOrder.setCoffee(coffee);
            coffee.getCoffeeOrders().add(coffeeOrder);
            return this;
        }

        public CoffeeOrderBuilder delivery(Delivery delivery) {
            coffeeOrder.setDelivery(delivery);
            delivery.getCoffeeOrders().add(coffeeOrder);
            return this;
        }

        public CoffeeOrder build() {
            return coffeeOrder;
        }
    }
}
