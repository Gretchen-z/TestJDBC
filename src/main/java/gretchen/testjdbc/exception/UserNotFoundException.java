package gretchen.testjdbc.exception;

public class UserNotFoundException extends Exception {

    private Long customerID;

    public UserNotFoundException(Long customerID) {
        this.customerID = customerID;
    }

    @Override
    public String getMessage() {
        return "Пользователь с ID " + customerID + " не найден!";
    }
}
