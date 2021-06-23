package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.SecurityOrderDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.domain.TraderAccountView;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TraderAccountService {

  private static final Logger logger = LoggerFactory.getLogger(TraderAccountService.class);

  private TraderDao traderDao;
  private AccountDao accountDao;
  private SecurityOrderDao securityOrderDao;
  private PositionDao positionDao;

  @Autowired
  public TraderAccountService(TraderDao traderDao, AccountDao accountDao,
      SecurityOrderDao securityOrderDao, PositionDao positionDao) {
    this.traderDao = traderDao;
    this.accountDao = accountDao;
    this.securityOrderDao = securityOrderDao;
    this.positionDao = positionDao;
  }

  /**
   * Creates a new trader and initializes an account
   * @param trader
   * @return TraderAccountView
   * @throws IllegalArgumentException if a trader has null fields or non null id
   */
  public TraderAccountView createTraderAndAccount(Trader trader) {
    Trader createTrader = validateAndCreateTrader(trader);
    Account createdAccount = createAccountForTrader(createTrader);
    return createTraderAccountView(createTrader, createdAccount);
  }

  /**
   * Helper method
   * @param trader
   * @return created Trader
   * @throws IllegalArgumentException if any of the fields are null or id is not null
   */
  private Trader validateAndCreateTrader(Trader trader) {
    if (trader.getFirstName() == null
        || trader.getLastName() == null
        || trader.getEmail() == null
        || trader.getDob() == null
        || trader.getCountry() == null) {
      throw new IllegalArgumentException("All fields in trader must be non null.");
    }
    if (trader.getId() != null) {
      throw new IllegalArgumentException("Id must be null.");
    }
    return traderDao.save(trader);
  }

  /**
   * Helper method
   * @param trader
   * @return created Account
   */
  private Account createAccountForTrader(Trader trader) {
    Account account = new Account();
    account.setAmount(0.0);
    account.setTraderId(trader.getId());
    return accountDao.save(account);
  }

  /**
   * Helper method
   * @param trader
   * @param account
   * @return view
   */
  private TraderAccountView createTraderAccountView(Trader trader, Account account) {
    TraderAccountView traderAccountView = new TraderAccountView();
    traderAccountView.setAccountId(account.getId());
    traderAccountView.setTraderId(trader.getId());
    traderAccountView.setFirstName(trader.getFirstName());
    traderAccountView.setLastName(trader.getLastName());
    traderAccountView.setDob(trader.getDob());
    traderAccountView.setCountry(trader.getCountry());
    traderAccountView.setEmail(trader.getEmail());
    traderAccountView.setAmount(account.getAmount());
    return traderAccountView;
  }

  /**
   * A trader is deleted if it has no open position and 0 balance
   * @param traderId must be not null
   * @throws IllegalArgumentException if traderId is null/not found/unable to delete
   */
  @Transactional
  public void deleteTraderById(Integer traderId) {
    if (traderId == null) {
      throw new IllegalArgumentException("Must provide a valid trader Id");
    }
    Trader trader = traderDao.findById(traderId)
        .orElseThrow(() -> new IllegalArgumentException("Couldn't find trader with id " + traderId));
    Account account = accountDao.findAccountByTraderId(trader.getId());
    if (account.getAmount() != 0) {
      throw new IllegalArgumentException("Account balance is not zero.");
    }
    List<SecurityOrder> securityOrders = securityOrderDao.findAllByAccountId(account.getId());
    for (SecurityOrder securityOrder : securityOrders) {
      securityOrderDao.delete(securityOrder);
    }
    accountDao.delete(account);
    traderDao.delete(trader);
  }

  /**
   * Deposit a fund to an account owned by traderId
   * @param traderId must be non null
   * @param fund greater than 0
   * @return updated account
   * @throws IllegalArgumentException if traderId is null/not found or fund is less than 0
   */
  @Transactional
  public Account deposit(Integer traderId, Double fund) {
    if (traderId == null) {
      throw new IllegalArgumentException("Must provide a valid trader Id");
    }
    Trader trader = traderDao.findById(traderId)
        .orElseThrow(() -> new IllegalArgumentException("Couldn't find trader with id " + traderId));
    if (fund == null || fund <= 0) {
      throw new IllegalArgumentException("Fund must be greater than zero.");
    }
    Account account = accountDao.findAccountByTraderId(trader.getId());
    account.setAmount(account.getAmount() + fund);
    return accountDao.save(account);
  }
  /**
   * Withdraws fund from an account owned by traderId
   * @param traderId must be non null
   * @param fund greater than 0
   * @return updated account
   * @throws IllegalArgumentException if traderId is null/not found or fund is less than 0 or insufficient funds
   */
  @Transactional
  public Account withdraw(Integer traderId, Double fund) {
    if (traderId == null) {
      throw new IllegalArgumentException("Must provide a valid trader Id");
    }
    Trader trader = traderDao.findById(traderId)
        .orElseThrow(() -> new IllegalArgumentException("Couldn't find trader with id " + traderId));
    if (fund == null || fund <= 0) {
      throw new IllegalArgumentException("Fund must be greater than zero.");
    }
    Account account = accountDao.findAccountByTraderId(trader.getId());
    if (account.getAmount() >= fund) {
      account.setAmount(account.getAmount() - fund);
    } else {
      throw new IllegalArgumentException("Insufficient funds");
    }
    return accountDao.save(account);
  }

}
