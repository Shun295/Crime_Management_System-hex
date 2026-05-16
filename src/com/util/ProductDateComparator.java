package com.util;

import com.model.Product;

import java.util.Comparator;

public class ProductDateComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return p1.getDateOfPublish().compareTo(p2.getDateOfPublish());
    }
}
