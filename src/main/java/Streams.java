// SPDX-License-Identifier: MIT

import model.Order;
import services.OrderRepository;

import java.util.Arrays;
import java.util.stream.Stream;

import static services.Products.APPLE;

public class Streams {

    public static void main(String[] args) {

        // compute the number of apples to be delivered to post codes 45...
        // but only consider the first two orders from these post codes
        Order[] orders = OrderRepository.getOrders();

        int applesToEssen = Arrays.stream(orders)
                .filter(  o -> o.getCustomer().getPostCode().startsWith("45"))
                .limit(2) // max two orders from essen
                .flatMap( o -> Arrays.stream(o.getItems()))
                .filter(  i -> i.getProduct() == APPLE)
                .map(     i -> i.getQuantity())
                .reduce(0, (subtotal, qty) -> subtotal + qty);

        System.out.println("Apples to Essen: " + applesToEssen);
    }
}
