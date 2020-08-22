package com.practice.fetchanddisplayproducts;

import org.json.JSONObject;

public class ProductInfo extends JSONObject {
    String productName;
    String productDetails;
    String productPrice;

    public String getProductName() {
        return productName;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public String getProductPrice() {
        return productPrice;
    }
}
