package music.infrastructure.repository.account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import music.domain.model.account.Account;
import music.domain.model.account.AccountId;
import music.domain.model.account.AccountRepository;

@Repository
public interface MemberAccountRepositoryJpa extends JpaRepository<Account, String>, AccountRepository {

    default List<Account> search() {
        return findAll();
    }

    default Account store(Account account) {
        return save(account);
    }

    default Account update(Account account) {
        return save(account);
    }

    default Boolean existsByAccountId(AccountId accountId) {
        return existsById(accountId.value());
    }

    default Account load(AccountId accountId) {
        return findById(accountId.value()).orElseThrow(() -> new RuntimeException("メンバーアカウントが見つかりませんでした。"));
    }

    default Account loadByLoginId(String loginId) {
        return findById(loginId).orElseThrow(() -> new RuntimeException("Account not found"));
    }

}