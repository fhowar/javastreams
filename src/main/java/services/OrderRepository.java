// SPDX-License-Identifier: MIT

package services;

import model.Customer;
import model.Order;
import model.OrderItem;

import static services.Products.*;

public class OrderRepository {

    public static Order[] getOrders() {

        final Customer falk = new Customer("Falk", "45149");
        final Customer julia = new Customer("Julia", "45149");
        final Customer vishwa = new Customer("Vishwa", "94086");

        final Order falksOrder1  = new Order(falk,
                new OrderItem(PIZZA, 2),
                new OrderItem(APPLE, 3));

        final Order juliasOrder  = new Order(julia,
                new OrderItem(BURGER, 5),
                new OrderItem(APPLE, 1));

        final Order falksOrder2  = new Order(falk,
                new OrderItem(APPLE, 15));

        final Order vishwasOrder = new Order(vishwa,
                new OrderItem(PIZZA, 1),
                new OrderItem(BURGER, 1));

        return new Order[] {falksOrder1, juliasOrder, falksOrder2, vishwasOrder};
    }
}
