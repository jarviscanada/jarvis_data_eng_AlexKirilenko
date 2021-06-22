package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.model.domain.Trader;
import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TraderDaoIntTest {

  @Autowired
  private TraderDao traderDao;

  @Before
  public void setup() {
    traderDao.deleteAll();
  }

  @After
  public void cleanup() {
    traderDao.deleteAll();
  }

  @Test
  public void basicTest() {
    Trader trader1 = new Trader();
    trader1.setFirstName("Bob");
    trader1.setLastName("One");
    trader1.setDob(new Date(2000, 1, 1));
    trader1.setCountry("Canada");
    trader1.setEmail("bob@gmail.com");

    Trader trader2 = new Trader();
    trader2.setFirstName("Alice");
    trader2.setLastName("Two");
    trader2.setDob(new Date(2000, 12, 30));
    trader2.setCountry("Canada");
    trader2.setEmail("alice@gmail.com");

    Trader trader1Saved = traderDao.save(trader1);
    Trader trader2Saved = traderDao.save(trader2);

    Trader trader1Retrieved = traderDao.findById(trader1Saved.getId()).get();
    Trader trader2Retrieved = traderDao.findById(trader2Saved.getId()).get();

    assertEquals(trader1.getCountry(), trader1Retrieved.getCountry());
    assertEquals(trader2.getCountry(), trader2Retrieved.getCountry());

    assertEquals(trader1.getEmail(), trader1Retrieved.getEmail());
    assertEquals(trader2.getEmail(), trader2Retrieved.getEmail());

    assertEquals("Alice", traderDao.findAllByLastName("Two").get(0).getFirstName());
    assertEquals(trader2.getDob(), traderDao.findAllByLastName("Two").get(0).getDob());

  }

}