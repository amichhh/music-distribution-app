package music.infrastructure.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import music.domain.model.account.AccountId;
import music.domain.model.account.Belong;
import music.domain.model.account.BelongRepository;
import music.infrastructure.shared.exception.DataNotFoundException;

@Repository
public interface BelongRepositoryJpa extends JpaRepository<Belong, String>, BelongRepository {

    default Belong load(AccountId accountId) {
        return findById(accountId.value()).orElseThrow(() -> new DataNotFoundException("所属先データが見つかりませんでした。"));
    }

    default Belong store(Belong belong) {
        return save(belong);
    }

}
