package ca.jrvs.apps.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class JDBCExecutor {

  public static void main(String[] args) {
    DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
        "javajdbc", "postgres", "password");
    try {
      Connection connection = dcm.getConnection();

      OrderDAO orderDAO = new OrderDAO(connection);

      Order order = orderDAO.findById(1003);

      System.out.println(order);


    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

}
