package ca.jrvs.apps.trading.service;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.domain.TraderAccountView;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TraderAccountServiceIntTest {

  @Autowired
  private TraderAccountService traderAccountService;

  @Autowired
  private TraderDao traderDao;
  @Autowired
  private AccountDao accountDao;

  @Test
  public void createTraderAndAccount() {
    Trader trader = new Trader();
    trader.setFirstName("Bob");
    trader.setLastName("One");
    trader.setDob(new Date(2000, 1, 1));
    trader.setCountry("Canada");
    trader.setEmail("bob@gmail.com");

    TraderAccountView traderAccountView = traderAccountService.createTraderAndAccount(trader);

    assertEquals(trader.getEmail(), traderAccountView.getEmail());
    assertNotNull(traderAccountView.getTraderId());
    assertNotNull(traderAccountView.getAccountId());
  }

  @Test
  public void deleteTraderById() {
    Trader trader = new Trader();
    trader.setFirstName("Bob");
    trader.setLastName("One");
    trader.setDob(new Date(2000, 1, 1));
    trader.setCountry("Canada");
    trader.setEmail("bob@gmail.com");

    TraderAccountView traderAccountView = traderAccountService.createTraderAndAccount(trader);

    traderAccountService.deleteTraderById(traderAccountView.getTraderId());

    try {
      Trader trader1 = traderDao.findById(traderAccountView.getTraderId())
          .orElseThrow(() -> new IllegalArgumentException("No such trader"));
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

  }

  @Test
  public void deposit() {
    Trader trader = new Trader();
    trader.setFirstName("Bob");
    trader.setLastName("One");
    trader.setDob(new Date(2000, 1, 1));
    trader.setCountry("Canada");
    trader.setEmail("bob@gmail.com");
    TraderAccountView traderAccountView = traderAccountService.createTraderAndAccount(trader);

    assertEquals(traderAccountView.getAmount(), accountDao.findById(traderAccountView.getAccountId()).get().getAmount(), 0.001);

    Account account1 = traderAccountService.deposit(traderAccountView.getTraderId(), 100.0);
    assertEquals(account1.getAmount(), accountDao.findById(traderAccountView.getAccountId()).get().getAmount(), 0.001);

    try {
      traderAccountService.deposit(traderAccountView.getTraderId(), -100.0);
      fail();
    } catch (Exception e) {
      assertTrue(true);
    }

    try {
      traderAccountService.deposit(Integer.MIN_VALUE, 100.0);
      fail();
    } catch (Exception e) {
      assertTrue(true);
    }

  }

  @Test
  public void withdraw() {
    Trader trader = new Trader();
    trader.setFirstName("Bob");
    trader.setLastName("One");
    trader.setDob(new Date(2000, 1, 1));
    trader.setCountry("Canada");
    trader.setEmail("bob@gmail.com");
    TraderAccountView traderAccountView = traderAccountService.createTraderAndAccount(trader);

    assertEquals(traderAccountView.getAmount(), accountDao.findById(traderAccountView.getAccountId()).get().getAmount(), 0.001);

    Account account1 = traderAccountService.deposit(traderAccountView.getTraderId(), 100.0);
    assertEquals(account1.getAmount(), accountDao.findById(traderAccountView.getAccountId()).get().getAmount(), 0.001);

    Account account2 = traderAccountService.withdraw(traderAccountView.getTraderId(), 50.0);
    assertEquals(account2.getAmount(), accountDao.findById(traderAccountView.getAccountId()).get().getAmount(), 0.001);


    try {
      traderAccountService.withdraw(traderAccountView.getTraderId(), -100.0);
      fail();
    } catch (Exception e) {
      assertTrue(true);
    }

    try {
      traderAccountService.withdraw(traderAccountView.getTraderId(), 100.0);
      fail();
    } catch (Exception e) {
      assertTrue(true);
    }
  }
}