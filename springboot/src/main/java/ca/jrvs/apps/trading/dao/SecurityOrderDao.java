package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityOrderDao extends JpaRepository<SecurityOrder, Integer> {
  static final Logger logger = LoggerFactory.getLogger(SecurityOrder.class);

  List<SecurityOrder> findAllByAccountId(Integer accountId);
}
