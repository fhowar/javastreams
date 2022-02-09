// SPDX-License-Identifier: MIT

import model.Order;
import model.OrderItem;
import services.OrderRepository;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

import static services.Products.APPLE;

public class Pipeline {

    interface Node<T> {
        void accept(T in);
    }

    public static void main(String[] args) {

        // compute the number of apples to be delivered to post codes 45...
        // but only consider the first two orders from these post codes

        Order[] orders = OrderRepository.getOrders();

        Stream<Order> stream =  Arrays.stream(orders);
        Iterator<Order> it = stream.iterator();

        final boolean[] cancellationRequested = {false};
        final int[] applesToEssen = {0};

        final Node<Integer> qtyCount = new Node<>() {
            @Override
            public void accept(Integer in) {
                applesToEssen[0] += in;
            }
        };

        final Node<OrderItem> oiToQty = new Node<>() {
            @Override
            public void accept(OrderItem in) {
                qtyCount.accept(in.getQuantity());
            }
        };

        final Node<OrderItem> filterByProduct = new Node<>() {
            @Override
            public void accept(OrderItem in) {
                if (in.getProduct() == APPLE) {
                    qtyCount.accept(in.getQuantity());
                }
            }
        };

        final Node<Order> orderToItems = new Node<>() {
            @Override
            public void accept(Order in) {
                for (OrderItem oi : in.getItems()) {
                    filterByProduct.accept(oi);
                }
            }
        };

        final Node<Order> limitOrders = new Node<>() {
            private int count = 0;
            @Override
            public void accept(Order in) {
                if (++count <= 2) {
                    orderToItems.accept(in);
                }
                else {
                    cancellationRequested[0] = true;
                }
            }
        };

        final Node<Order> ordersFrom45 = new Node<>() {
            @Override
            public void accept(Order in) {
                if (in.getCustomer().getPostCode().startsWith("45")) {
                    limitOrders.accept(in);
                }
            }
        };

        while (it.hasNext() && !cancellationRequested[0]) {
            Order o = it.next();
            ordersFrom45.accept(o);
        }

        System.out.println("Apples to Essen: " + applesToEssen[0]);
    }
}
