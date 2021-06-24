package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Quote;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// JPA repository so methods return List instead of Iterable
@Repository
public interface QuoteDao extends JpaRepository<Quote, String> {
  static final Logger logger = LoggerFactory.getLogger(QuoteDao.class);

}
