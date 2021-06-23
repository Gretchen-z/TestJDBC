package gretchen.testjdbc.dao;

import gretchen.testjdbc.entity.Customer;
import gretchen.testjdbc.exception.UserNotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO {

    //create
    void add(Customer customer) throws SQLException;

    //read
    List<Customer> getAll() throws SQLException;
    Customer getByID(Long customers_id) throws SQLException, UserNotFoundException;

    //update
    void update(Customer customer) throws SQLException;

    //delete
    void remove(Customer customer) throws SQLException;
}
