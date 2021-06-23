package gretchen.testjdbc.entity;

import java.util.Objects;

public class Customer {

    private Long customerID;
    private String firstName;
    private String lastName;
    private Double balance;

    public Customer() { }

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerID, customer.customerID) &&
                Objects.equals(firstName, customer.firstName) &&
                Objects.equals(lastName, customer.lastName) &&
                Objects.equals(balance, customer.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerID, firstName, lastName, balance);
    }

    @Override
    public String toString() {
        return "Customers{" +
                "customer_id=" + customerID +
                ", first_name='" + firstName + '\'' +
                ", last_name='" + lastName + '\'' +
                ", balance=" + balance +
                '}';
    }
}
