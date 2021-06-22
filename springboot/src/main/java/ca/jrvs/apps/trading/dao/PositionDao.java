package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Position;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionDao extends JpaRepository<Position, String> {

  List<Position> findPositionsByAccountId(Integer accountId);

  List<Position> findPositionsByAccountIdAndTicker(Integer accountId, String ticker);

}
