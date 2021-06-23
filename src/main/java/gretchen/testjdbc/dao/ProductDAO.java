package gretchen.testjdbc.dao;

import gretchen.testjdbc.entity.Product;
import gretchen.testjdbc.exception.ProductNotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {

    //create
    void add(Product product) throws SQLException;

    //read
    List<Product> getAll() throws SQLException;
    Product getByID(Long product_id) throws SQLException, ProductNotFoundException;

    //update
    void update(Product product) throws SQLException;

    //delete
    void remove(Product product) throws SQLException;
}
