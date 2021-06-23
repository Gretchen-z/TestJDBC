package gretchen.testjdbc.entity;

import java.util.Objects;

public class Product {

    private Long productID;
    private String name;
    private Double price;
    private Integer inStock;

    public Product() { }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getInStock() {
        return inStock;
    }

    public void setInStock(Integer inStock) {
        this.inStock = inStock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productID, product.productID) &&
                Objects.equals(name, product.name) &&
                Objects.equals(price, product.price) &&
                Objects.equals(inStock, product.inStock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productID, name, price, inStock);
    }

    @Override
    public String toString() {
        return "gretchen.testjdbc.entity.Goods{" +
                "product_id=" + productID +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", in_stock=" + inStock +
                '}';
    }
}
