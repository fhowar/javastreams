// SPDX-License-Identifier: MIT

package model;

import java.util.Arrays;

public class Order {

    private final Customer customer;

    private final OrderItem[] items;

    public Order(Customer customer, OrderItem ... items) {
        this.customer = customer;
        this.items = items;
    }

    public OrderItem[] getItems() {
        return items;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "customer=" + customer +
                ", items=" + Arrays.toString(items) +
                '}';
    }
}
