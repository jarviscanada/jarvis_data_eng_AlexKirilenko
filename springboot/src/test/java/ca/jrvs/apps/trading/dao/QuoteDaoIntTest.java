package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.Arrays;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//@DataJpaTest // alternative to
@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE) // uses existing database (From application.properties in test)
//@ComponentScan(basePackages = "ca.jrvs.apps.trading")
//@Rollback(value = false) // prevents rollback by @DataJpaTest
public class QuoteDaoIntTest {


  @Autowired
  private QuoteDao quoteDao;

  @Before
  public void setup() {
    quoteDao.deleteAll();
  }

  @After
  public void cleanup() {
    quoteDao.deleteAll();
  }



  @Test
  public void basicTest() {
    Quote q1 = new Quote();
    q1.setTicker("AAPL");
    q1.setLastPrice(100.123d);
    q1.setAskPrice(123.1d);
    q1.setAskSize(222);
    q1.setBidPrice(124.1d);
    q1.setBidSize(321);
    quoteDao.save(q1);
    assertEquals(q1.getLastPrice(), quoteDao.findById("AAPL").get().getLastPrice());

    Quote q2 = new Quote();
    q2.setTicker("TSLA");
    q2.setLastPrice(201.5);
    q2.setAskPrice(202.1);
    q2.setAskSize(50);
    q2.setBidPrice(202.7);
    q2.setBidSize(150);
    quoteDao.save(q2);

    assertEquals(2, quoteDao.findAllById(Arrays.asList("AAPL", "TSLA")).size());
  }
}