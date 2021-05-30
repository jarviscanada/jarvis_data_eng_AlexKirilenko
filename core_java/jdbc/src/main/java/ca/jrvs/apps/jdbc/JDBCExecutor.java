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

      CustomerDAO customerDAO = new CustomerDAO(connection);


//      Customer customer = customerDAO.findById(10001);
//      System.out.println(customer);
//      customer.setEmail("gwash@wh.gov");
//      customerDAO.update(customer);
//      System.out.println(customerDAO.findById(10001));

//      List<Customer> customers = customerDAO.findAll();
//      System.out.println(customers.size());
//      for (Customer customer : customers) {
//        System.out.println(customer);
//      }


    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

}
