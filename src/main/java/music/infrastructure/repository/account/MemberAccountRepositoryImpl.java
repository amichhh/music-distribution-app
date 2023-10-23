package music.infrastructure.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;

import music.domain.model.account.AccountId;
import music.domain.model.account.MemberAccount;
import music.domain.model.account.MemberAccountRepository;

public interface MemberAccountRepositoryImpl extends JpaRepository<MemberAccount, String>, MemberAccountRepository {

    default MemberAccount save(MemberAccount account) {
        return save(account);
    };

    default MemberAccount load(AccountId accountId) {
        return findById(accountId.value()).orElseThrow(() -> new RuntimeException("メンバーアカウントが見つかりませんでした。"));
    }

    default MemberAccount loadByLoginId(String loginId) {
        return findById(loginId).orElseThrow(() -> new RuntimeException("アカウントが見つかりませんでした。"));
    };

    default Boolean existsByAccountId(AccountId accountId) {
        return existsById(accountId.value());
    }

}
