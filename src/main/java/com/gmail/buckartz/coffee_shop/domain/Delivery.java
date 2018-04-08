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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "delivery")
@Getter
@Setter
@EqualsAndHashCode(exclude = "coffeeOrders")
@ToString(exclude = "coffeeOrders")
public class Delivery {
    @Id
    @SequenceGenerator(name = "delivery_seq", sequenceName = "delivery_seq", allocationSize = 0)
    @GeneratedValue(generator = "delivery_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "time_from")
    private LocalTime timeFrom;

    @Column(name = "time_to")
    private LocalTime timeTo;

    @Column(name = "price")
    private Integer price;

    @OneToMany(mappedBy = "delivery")
    private Set<CoffeeOrder> coffeeOrders = new HashSet<>();

    public static DeliveryBuilder builder() {
        return new DeliveryBuilder();
    }

    public static class DeliveryBuilder {
        private Delivery delivery = new Delivery();

        private DeliveryBuilder() {
        }

        public DeliveryBuilder date(LocalDate date) {
            delivery.setDate(date);
            return this;
        }

        public DeliveryBuilder timeFrom(LocalTime timeFrom) {
            delivery.setTimeFrom(timeFrom);
            return this;
        }

        public DeliveryBuilder timeTo(LocalTime timeTo) {
            delivery.setTimeTo(timeTo);
            return this;
        }

        public DeliveryBuilder price(Integer price) {
            delivery.setPrice(price);
            return this;
        }

        public DeliveryBuilder coffeeOrders(CoffeeOrder coffeeOrder) {
            delivery.getCoffeeOrders().add(coffeeOrder);
            coffeeOrder.setDelivery(delivery);
            return this;
        }

        public Delivery build() {
            return delivery;
        }
    }
}
