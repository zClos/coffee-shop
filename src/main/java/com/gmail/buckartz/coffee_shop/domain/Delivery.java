package com.gmail.buckartz.coffee_shop.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "delivery")
@Data
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

    public DeliveryBuilder builder() {
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
    }
}
