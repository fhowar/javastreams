// SPDX-License-Identifier: MIT

import model.Order;
import model.OrderItem;
import services.OrderRepository;

import static services.Products.APPLE;

public class NestedLoops {

  public static void main(String[] args) {

    // compute the number of apples to be delivered to post codes 45...
    // but only consider the first two orders from these post codes

    Order[] orders = OrderRepository.getOrders();

    int applesToEssen = 0;
    int count = 0;

    for (Order o : orders) {
      if (o.getCustomer().getPostCode().startsWith("45")) {
        for (OrderItem oi : o.getItems()) {
          if (oi.getProduct() == APPLE) {
            applesToEssen += oi.getQuantity();
          }
        }
        if (++count == 2) {
          break;
        }
      }
    }

    System.out.println("Apples to Essen: " + applesToEssen);
  }
}
