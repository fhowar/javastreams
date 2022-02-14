// SPDX-License-Identifier: MIT

import model.Order;
import model.OrderItem;
import services.OrderRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static services.Products.APPLE;

public class Loops {

    public static void main(String[] args) {

        // compute the number of apples to be delivered to post codes 45...
        // but only consider the first two orders from these post codes
        Order[] orders = OrderRepository.getOrders();

        // orders from 45
        // filter: List<Order> -> List<Order>
        List<Order> ordersFrom45 = new ArrayList<>();
        for (Order o : orders) {
            if (o.getCustomer().getPostCode().startsWith("45")) {
                ordersFrom45.add(o);
            }
        }

        // restrict to first 2
        // limit: List<Order> -> List<Order>
        List<Order> ordersHead = new ArrayList<>();
        ordersHead.addAll(ordersFrom45.subList(0, Math.min(2, ordersFrom45.size())));

        // get items
        // map and flatten: List<Order> -> List<OrderItem>
        List<OrderItem> orderItems = new ArrayList<>();
        for (Order o : ordersHead) {
            orderItems.addAll(Arrays.asList(o.getItems()));
        }

        // restrict to apples
        // filter: List<OrderItem> -> List<OrderItem>
        List<OrderItem> appleItems = new ArrayList<>();
        for (OrderItem oi : orderItems) {
            if (oi.getProduct() == APPLE) {
                appleItems.add(oi);
            }
        }

        // count
        // reduce: List<OrderItem> -> Integer
        int applesToEssen = 0;
        for (OrderItem oi : appleItems) {
            applesToEssen += oi.getQuantity();
        }

        System.out.println("Apples to Essen: " + applesToEssen);
    }
}
