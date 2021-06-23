package gretchen.testjdbc.service.impl;

import gretchen.testjdbc.dao.CustomerDAO;
import gretchen.testjdbc.dao.ProductDAO;
import gretchen.testjdbc.dto.BuyRequestDTO;
import gretchen.testjdbc.entity.Customer;
import gretchen.testjdbc.entity.Product;
import gretchen.testjdbc.exception.ProductNotFoundException;
import gretchen.testjdbc.exception.UserNotFoundException;
import gretchen.testjdbc.service.ShopService;

import java.sql.SQLException;

public class ShopServiceImpl implements ShopService {
    private final CustomerDAO customerDAO;
    private final ProductDAO productDAO;

    public ShopServiceImpl(CustomerDAO customerDAO, ProductDAO productDAO) {
        this.customerDAO = customerDAO;
        this.productDAO = productDAO;
    }

    @Override
    public BuyRequestDTO buyProduct(int customerID, int productID) {
        BuyRequestDTO buyRequestDTO = new BuyRequestDTO();

        try {
            Customer customer = customerDAO.getByID((long) customerID);
            Product product = productDAO.getByID((long) productID);

            if (customer.getBalance() >= product.getPrice() && product.getInStock() > 0) {
                customer.setBalance(customer.getBalance() - product.getPrice());
                product.setInStock(product.getInStock() - 1);
                customerDAO.update(customer);
                productDAO.update(product);
                buyRequestDTO.setMessage(customer.getFirstName() + " " + customer.getLastName()
                        + " купил " + product.getName());
            } else if (customer.getBalance() >= product.getPrice() && product.getInStock() == 0) {
                buyRequestDTO.setMessage("Товар отсутствует на складе");
            } else if (customer.getBalance() < product.getPrice() && product.getInStock() > 0) {
                buyRequestDTO.setMessage("У покупателя недостаточно денежных средств для совершения покупки");
            } else if (customer.getBalance() < product.getPrice() && product.getInStock() == 0) {
                buyRequestDTO.setMessage("У покупателя недостаточно денежных средств для совершения покупки" +
                        " и товар отсутствует на складе");
            }
        } catch (SQLException e) {
            buyRequestDTO.setMessage("Сервис временно недоступен! Пприносим свои извинения.");
        } catch (UserNotFoundException e) {
            buyRequestDTO.setMessage(e.getMessage());
        } catch (ProductNotFoundException e) {
            buyRequestDTO.setMessage(e.getMessage());
        }
        return buyRequestDTO;
    }
}
