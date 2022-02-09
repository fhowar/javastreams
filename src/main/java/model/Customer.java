// SPDX-License-Identifier: MIT

package model;

public class Customer {

    private final String name;

    private final String postCode;

    public Customer(String name, String postcode) {
        this.name = name;
        this.postCode = postcode;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", postCode='" + postCode + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getPostCode() {
        return postCode;
    }
}
