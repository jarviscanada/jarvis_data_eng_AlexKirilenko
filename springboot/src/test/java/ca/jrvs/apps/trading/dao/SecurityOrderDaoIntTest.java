package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
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
public class SecurityOrderDaoIntTest {

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

    Quote quote = new Quote();
    quote.setTicker("TSLA");
    quote.setLastPrice(201.5);
    quote.setAskPrice(202.1);
    quote.setAskSize(50);
    quote.setBidPrice(202.7);
    quote.setBidSize(150);
    Quote quoteSaved = quoteDao.save(quote);

    SecurityOrder order = new SecurityOrder();
    order.setAccountId(accountSaved.getId());
    order.setStatus("FILLED");
    order.setTicker(quoteSaved.getTicker());
    order.setSize(50);
    order.setPrice(222.1);
    order.setNotes("abc");

    SecurityOrder orderSaved = securityOrderDao.save(order);

    SecurityOrder orderRetrived = securityOrderDao.findById(orderSaved.getId()).get();

    assertEquals(order.getAccountId(), orderRetrived.getAccountId());
    assertEquals(order.getPrice(), orderRetrived.getPrice());
    assertEquals(order.getSize(), orderRetrived.getSize());

    assertEquals(order.getPrice(), securityOrderDao.findAllByAccountId(accountSaved.getId()).get(0).getPrice());


  }

}