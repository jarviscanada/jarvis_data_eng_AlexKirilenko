package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.domain.Trader;
import java.util.Date;
import java.util.List;
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
public class PositionDaoIntTest {

  @Autowired
  private PositionDao positionDao;
  @Autowired
  private SecurityOrderDao securityOrderDao;
  @Autowired
  private AccountDao accountDao;
  @Autowired
  private TraderDao traderDao;
  @Autowired
  private QuoteDao quoteDao;


  @Before
  public void setup() {
    securityOrderDao.deleteAll();
    accountDao.deleteAll();
    traderDao.deleteAll();
    quoteDao.deleteAll();
  }

  @After
  public void cleanup() {
    securityOrderDao.deleteAll();
    accountDao.deleteAll();
    traderDao.deleteAll();
    quoteDao.deleteAll();
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

    Account account = new Account();
    account.setTraderId(traderSaved.getId());
    account.setAmount(123.1);
    Account accountSaved = accountDao.save(account);

    Quote quote1 = new Quote();
    quote1.setTicker("TSLA");
    quote1.setLastPrice(201.5);
    quote1.setAskPrice(202.1);
    quote1.setAskSize(50);
    quote1.setBidPrice(202.7);
    quote1.setBidSize(150);
    Quote quote1Saved = quoteDao.save(quote1);

    Quote quote2 = new Quote();
    quote2.setTicker("GOOG");
    quote2.setLastPrice(1201.5);
    quote2.setAskPrice(1202.1);
    quote2.setAskSize(99);
    quote2.setBidPrice(1202.7);
    quote2.setBidSize(212);
    Quote quote2Saved = quoteDao.save(quote2);

    SecurityOrder order1 = new SecurityOrder();
    order1.setAccountId(accountSaved.getId());
    order1.setStatus("FILLED");
    order1.setTicker(quote1Saved.getTicker());
    order1.setSize(50);
    order1.setPrice(200.43);
    order1.setNotes("abc");

    SecurityOrder order2 = new SecurityOrder();
    order2.setAccountId(accountSaved.getId());
    order2.setStatus("FILLED");
    order2.setTicker(quote1Saved.getTicker());
    order2.setSize(15);
    order2.setPrice(199.7);
    order2.setNotes("abc");

    SecurityOrder order3 = new SecurityOrder();
    order3.setAccountId(accountSaved.getId());
    order3.setStatus("FILLED");
    order3.setTicker(quote2Saved.getTicker());
    order3.setSize(135);
    order3.setPrice(1205.3);
    order3.setNotes("abc");

    SecurityOrder order1Saved = securityOrderDao.save(order1);
    SecurityOrder order2Saved = securityOrderDao.save(order2);
    SecurityOrder order3Saved = securityOrderDao.save(order3);

    List<Position> positionsAll = positionDao.findPositionsByAccountId(accountSaved.getId());
    assertEquals(2, positionsAll.size());
    assertEquals(accountSaved.getId(), positionsAll.get(0).getAccountId());

    List<Position> positionsTicker =
        positionDao.findPositionsByAccountIdAndTicker(accountSaved.getId(), order1Saved.getTicker());
    Integer totalSizeForOrders1And2 = order1Saved.getSize() + order2Saved.getSize();
    assertEquals(Long.valueOf(totalSizeForOrders1And2), positionsTicker.get(0).getPosition());
    assertEquals(order1Saved.getTicker(), positionsTicker.get(0).getTicker());

  }
}