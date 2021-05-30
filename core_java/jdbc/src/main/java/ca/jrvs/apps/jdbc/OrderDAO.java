package ca.jrvs.apps.jdbc;

import ca.jrvs.apps.jdbc.util.DataAccessObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class OrderDAO extends DataAccessObject<Order> {

  private static final String GET_ORDER_BY_ID = "SELECT\n"
      + "  c.first_name c_first_name, c.last_name c_last_name,"
      + " c.email c_email, o.order_id o_order_id,\n"
      + "  o.creation_date o_creation_date, o.total_due o_total_due, "
      + "o.status o_status,\n"
      + "  s.first_name s_first_name, s.last_name s_last_name, s.email s_email,\n"
      + "  ol.quantity ol_quantity,\n"
      + "  p.code p_code, p.name p_name, p.size p_size,"
      + " p.variety p_variety, p.price p_price\n"
      + "FROM orders o\n"
      + "  JOIN customer c ON o.customer_id = c.customer_id\n"
      + "  JOIN salesperson s ON o.salesperson_id=s.salesperson_id\n"
      + "  JOIN order_item ol ON ol.order_id=o.order_id\n"
      + "  JOIN product p ON ol.product_id = p.product_id\n"
      + "WHERE o.order_id = ?;";

  public OrderDAO(Connection connection) {
    super(connection);
  }

  @Override
  public Order findById(long id) {
    Order order = null;
    try (PreparedStatement statement = this.connection.prepareStatement(GET_ORDER_BY_ID)) {
      statement.setLong(1, id);
      ResultSet rs = statement.executeQuery();
      order = new Order();
      while (rs.next()) {
        // parse customer if it hasn't been added yet
        if (order.getCustomer() == null) {
          Customer customer = new Customer();
          customer.setFirstName(rs.getString("c_first_name"));
          customer.setLastName(rs.getString("c_last_name"));
          customer.setEmail(rs.getString("c_email"));
          order.setCustomer(customer);
        }
        // parse salesperson if it hasn't been added yet
        if (order.getSalesperson() == null) {
          Salesperson salesperson = new Salesperson();
          salesperson.setFirstName(rs.getString("s_first_name"));
          salesperson.setLastName(rs.getString("s_last_name"));
          salesperson.setEmail(rs.getString("s_email"));
          order.setSalesperson(salesperson);
        }
        // set order info if it hasn't been added yet
        if (order.getId() == 0) {
          order.setId(rs.getLong("o_order_id"));
          order.setCreation_date(rs.getTimestamp("o_creation_date"));
          order.setTotal_due(rs.getDouble("o_total_due"));
          order.setStatus(rs.getString("o_status"));
        }

        Product product = new Product();
        product.setCode(rs.getString("p_code"));
        product.setName(rs.getString("p_name"));
        product.setSize(rs.getInt("p_size"));
        product.setVariety(rs.getString("p_variety"));
        product.setPrice(rs.getDouble("p_price"));

        // orderItem is composed of Product
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(rs.getInt("ol_quantity"));
        orderItem.setProduct(product);

        // initialize List with orderItems
        if (order.getOrderItems() == null) {
          order.setOrderItems(new LinkedList<>());
        }
        order.getOrderItems().add(orderItem);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
    return order;
  }

  @Override
  public List<Order> findAll() {
    return null;
  }

  @Override
  public Order update(Order dto) {
    return null;
  }

  @Override
  public Order create(Order dto) {
    return null;
  }

  @Override
  public void delete(long id) {

  }
}
