package gretchen.testjdbc;

import gretchen.testjdbc.controller.ShopServiceController;
import gretchen.testjdbc.controller.impl.ShopServiceControllerImpl;
import gretchen.testjdbc.dao.impl.ProductDAOImpl;
import gretchen.testjdbc.service.ShopService;
import gretchen.testjdbc.service.impl.ShopServiceImpl;
import gretchen.testjdbc.sqlJDBC.JDBCUtil;
import gretchen.testjdbc.dao.impl.CustomerDAOImpl;
import gretchen.testjdbc.view.View;
import gretchen.testjdbc.view.impl.ViewImpl;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        JDBCUtil util = new JDBCUtil();

        ShopService service = new ShopServiceImpl(new CustomerDAOImpl(), new ProductDAOImpl());
        ShopServiceController shopServiceController = new ShopServiceControllerImpl(service);
        View view = new ViewImpl(new Scanner(System.in), System.out, shopServiceController);
        view.start();

        util.disconnect();
    }
}
