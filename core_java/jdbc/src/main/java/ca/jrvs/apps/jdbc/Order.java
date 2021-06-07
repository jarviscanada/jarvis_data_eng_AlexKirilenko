package ca.jrvs.apps.jdbc;

import ca.jrvs.apps.jdbc.util.DataTransferObject;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Order implements DataTransferObject {

  private long id;
  private Timestamp creation_date;
  private double total_due;
  private String status;
  private Customer customer;
  private Salesperson salesperson;
  private List<OrderItem> orderItems;


  @Override
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Timestamp getCreation_date() {
    return creation_date;
  }

  public void setCreation_date(Timestamp creation_date) {
    this.creation_date = creation_date;
  }

  public double getTotal_due() {
    return total_due;
  }

  public void setTotal_due(double total_due) {
    this.total_due = total_due;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Salesperson getSalesperson() {
    return salesperson;
  }

  public void setSalesperson(Salesperson salesperson) {
    this.salesperson = salesperson;
  }

  public List<OrderItem> getOrderItems() {
    return orderItems;
  }

  public void setOrderItems(List<OrderItem> orderItems) {
    this.orderItems = orderItems;
  }

  @Override
  public String toString() {
    return "Order{" +
        "\n id=" + id +
        ",\n creation_date=" + creation_date +
        ",\n total_due=" + total_due +
        ",\n status='" + status + '\'' +
        ",\n customer=" + customer +
        ",\n salesperson=" + salesperson +
        ",\n orderItems=\n " + orderItems.stream().map(Objects::toString).collect(Collectors.joining("\n "))+
        '}';
  }
}
