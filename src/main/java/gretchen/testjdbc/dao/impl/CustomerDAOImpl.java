package gretchen.testjdbc.dao.impl;

import gretchen.testjdbc.exception.UserNotFoundException;
import gretchen.testjdbc.sqlJDBC.JDBCUtil;
import gretchen.testjdbc.dao.CustomerDAO;
import gretchen.testjdbc.entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl extends JDBCUtil implements CustomerDAO {

    Connection connection = getConnection();

    @Override
    public void add(Customer customer) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO public.\"Customers\"(\n" +
                "\tcustomer_id, first_name, last_name, balance)\n" +
                "\tVALUES (?, ?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, customer.getCustomerID());
            preparedStatement.setString(2, customer.getFirstName());
            preparedStatement.setString(3, customer.getLastName());
            preparedStatement.setDouble(4, customer.getBalance());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        List<Customer> customerList = new ArrayList<>();

        String sql = "SELECT customer_id, first_name, last_name, balance\n" +
                "\tFROM public.\"Customers\"";

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setCustomerID(resultSet.getLong("customer_id"));
                customer.setFirstName(resultSet.getString("first_name"));
                customer.setLastName(resultSet.getString("last_name"));
                customer.setBalance(resultSet.getDouble("balance"));

                customerList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return customerList;
    }

    @Override
    public Customer getByID(Long customerID) throws SQLException, UserNotFoundException {
        PreparedStatement preparedStatement = null;

        String sql = "SELECT * FROM \"Customers\" WHERE customer_id=?";

        Customer customer = null;

        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, customerID);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            customer = new Customer();
            customer.setCustomerID(resultSet.getLong("customer_id"));
            customer.setFirstName(resultSet.getString("first_name"));
            customer.setLastName(resultSet.getString("last_name"));
            customer.setBalance(resultSet.getDouble("balance"));
        } else {
            throw new UserNotFoundException(customerID);
        }

        preparedStatement.close();

        return customer;
    }

    @Override
    public void update(Customer customer) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE public.\"Customers\"\n" +
                "\tSET first_name=?, last_name=?, balance=? WHERE customer_id=?\n";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setDouble(3, customer.getBalance());
            preparedStatement.setLong(4, customer.getCustomerID());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    @Override
    public void remove(Customer customer) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM public.\"Customers\"\n" +
                "WHERE customer_id=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, customer.getCustomerID());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }
}
