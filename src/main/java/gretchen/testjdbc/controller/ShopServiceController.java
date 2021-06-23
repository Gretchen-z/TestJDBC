package gretchen.testjdbc.controller;

import gretchen.testjdbc.dto.BuyRequestDTO;

public interface ShopServiceController {
    public BuyRequestDTO buyProduct(int customerID, int productID);
}
