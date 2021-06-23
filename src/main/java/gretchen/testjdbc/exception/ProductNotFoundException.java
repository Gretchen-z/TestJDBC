package gretchen.testjdbc.exception;

public class ProductNotFoundException extends Exception {

    private Long productID;

    public ProductNotFoundException(Long productID) {
        this.productID = productID;
    }

    @Override
    public String getMessage() {
        return "Товар c ID " + productID + " не найден!";
    }
}
