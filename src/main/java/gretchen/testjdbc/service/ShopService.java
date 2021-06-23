package gretchen.testjdbc.service;

import gretchen.testjdbc.dto.BuyRequestDTO;

public interface ShopService {

    public BuyRequestDTO buyProduct(int customerID, int productID);
}
