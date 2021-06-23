package gretchen.testjdbc.dao.impl;

import gretchen.testjdbc.dao.ProductDAO;
import gretchen.testjdbc.entity.Product;
import gretchen.testjdbc.exception.ProductNotFoundException;
import gretchen.testjdbc.sqlJDBC.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl extends JDBCUtil implements ProductDAO {

    Connection connection = getConnection();

    @Override
    public void add(Product product) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO public.\"Goods\"(\n" +
                "\tproduct_id, product_name, price, in_stock)\n" +
                "\tVALUES (?, ?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, product.getProductID());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getInStock());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    @Override
    public List<Product> getAll() throws SQLException {
        List<Product> customersList = new ArrayList<>();

        String sql = "SELECT product_id, product_name, price, in_stock\n" +
                "\tFROM public.\"Goods\"";

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {
                Product product = new Product();
                product.setProductID(resultSet.getLong("product_id"));
                product.setName(resultSet.getString("product_name"));
                product.setPrice(resultSet.getDouble("price"));
                product.setInStock(resultSet.getInt("in_stock"));

                customersList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return customersList;
    }

    @Override
    public Product getByID(Long productID) throws SQLException, ProductNotFoundException {
        PreparedStatement preparedStatement = null;

        String sql = "SELECT * FROM \"Goods\" WHERE product_id=?";

        Product product = null;

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, productID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                product = new Product();
                product.setProductID(resultSet.getLong("product_id"));
                product.setName(resultSet.getString("product_name"));
                product.setPrice(resultSet.getDouble("price"));
                product.setInStock(resultSet.getInt("in_stock"));
            } else {
                throw new ProductNotFoundException(productID);
            }
                preparedStatement.close();

        return product;
    }

    @Override
    public void update(Product product) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE public.\"Goods\"\n" +
                "\tSET product_name=?, price=?, in_stock=? WHERE product_id=?\n";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getInStock());
            preparedStatement.setLong(4, product.getProductID());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    @Override
    public void remove(Product product) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM public.\"Goods\"\n" +
                "WHERE product_id=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, product.getProductID());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }
}
