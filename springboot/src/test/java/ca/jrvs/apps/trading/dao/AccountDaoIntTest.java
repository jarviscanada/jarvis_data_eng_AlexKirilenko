package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.model.domain.Account;
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
public class AccountDaoIntTest {

  @Autowired
  private AccountDao accountDao;

  @Autowired
  private TraderDao traderDao;

  @Before
  public void setup() {
    accountDao.deleteAll();
    traderDao.deleteAll();
  }

  @After
  public void cleanup() {
    accountDao.deleteAll();
    traderDao.deleteAll();
  }

  @Test
  public void basicTest() {
    Trader trader = new Trader();
    trader.setFirstName("Bob");
    trader.setLastName("One");
    trader.setDob(new Date(2000, 1, 1));
    trader.setCountry("Canada");
    trader.setEmail("bob@gmail.com");
    Trader traderSaved = traderDao.save(trader);

    Account account1 = new Account();
    account1.setTraderId(traderSaved.getId());
    account1.setAmount(123.1);


    Account account1Saved = accountDao.save(account1);

    Account account1Retrieved = accountDao.findById(account1Saved.getId()).get();

    assertEquals(account1.getAmount(), account1Retrieved.getAmount());
    assertEquals(account1.getTraderId(), account1Retrieved.getTraderId());

    assertEquals(account1.getAmount(), accountDao.findAccountByTraderId(trader.getId()).getAmount());
  }
}