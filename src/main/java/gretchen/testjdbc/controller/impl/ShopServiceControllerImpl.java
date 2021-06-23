package gretchen.testjdbc.controller.impl;

import gretchen.testjdbc.controller.ShopServiceController;
import gretchen.testjdbc.dto.BuyRequestDTO;
import gretchen.testjdbc.service.ShopService;

public class ShopServiceControllerImpl implements ShopServiceController {
    private ShopService shopService;

    public ShopServiceControllerImpl(ShopService shopService) {
        this.shopService = shopService;
    }

    @Override
    public BuyRequestDTO buyProduct(int customerID, int productID){
        BuyRequestDTO buyRequestDTO = shopService.buyProduct(customerID, productID);
        return buyRequestDTO;
    }
}
