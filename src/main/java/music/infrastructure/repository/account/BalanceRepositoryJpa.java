package music.infrastructure.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import music.domain.model.account.AccountId;
import music.domain.model.account.Balance;
import music.domain.model.account.BalanceRepository;
import music.infrastructure.shared.exception.DataNotFoundException;

@Repository
public interface BalanceRepositoryJpa extends JpaRepository<Balance, String>, BalanceRepository {

    default Balance load(AccountId accountId) {
        return findById(accountId.value()).orElseThrow(() -> new DataNotFoundException("残高データが見つかりませんでした。"));
    }

    default Balance store(Balance balance) {
        return save(balance);
    }

    default Balance update(Balance balance) {
        return save(balance);
    }
}
