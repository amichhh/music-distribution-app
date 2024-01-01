package music.infrastructure.repository.account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import music.domain.model.account.Account;
import music.domain.model.account.AccountId;
import music.domain.model.account.AccountRepository;
import music.infrastructure.shared.exception.DataNotFoundException;

@Repository
public interface AccountRepositoryJpa extends JpaRepository<Account, String>, AccountRepository {

    default Account load(AccountId accountId) {
        return findById(accountId.value()).orElseThrow(() -> new DataNotFoundException("アカウントデータが見つかりませんでした。"));
    }

    default Account loadByLoginId(String loginId) {
        return findById(loginId).orElseThrow(() -> new DataNotFoundException("アカウントが見つかりませんでした。"));
    }

    default List<Account> search() {
        return findAll();
    }

    default List<Account> searchMember() {
        return findMember();
    }

    default List<Account> searchBusiness() {
        return findBusiness();
    };

    default List<Account> searchStaff() {
        return findStaff();
    };

    default Account store(Account account) {
        return save(account);
    }

    default Account update(Account account) {
        return save(account);
    }

    default Boolean existsByAccountId(AccountId accountId) {
        return existsById(accountId.value());
    }

    @Query(value = "SELECT * FROM account a WHERE a.authority = MEMBER", nativeQuery = true)
    List<Account> findMember();

    @Query(value = "SELECT * FROM account a WHERE a.authority = BUSINESS", nativeQuery = true)
    List<Account> findBusiness();

    @Query(value = "SELECT * FROM account a WHERE a.authority = STAFF", nativeQuery = true)
    List<Account> findStaff();

}
