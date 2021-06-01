package ca.jrvs.apps.jdbc;

import ca.jrvs.apps.jdbc.util.DataTransferObject;

public class OrderItem implements DataTransferObject {

  private long id;
  private long order_id;
  private int quantity;
  private Product product;

  @Override
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getOrder_id() {
    return order_id;
  }

  public void setOrder_id(long order_id) {
    this.order_id = order_id;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  @Override
  public String toString() {
    return "OrderItem{" +
        "id=" + id +
        ", order_id=" + order_id +
        ", quantity=" + quantity +
        ", product=" + product +
        '}';
  }
}
