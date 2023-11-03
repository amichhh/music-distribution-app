package music.domain.model.account;

import java.util.List;

public interface AccountRepository {

    List<Account> search();

    List<Account> searchMember();

    List<Account> searchBusiness();

    List<Account> searchStaff();

    Account store(Account account);

    Account update(Account account);

    Account load(AccountId accountId);

    Account loadByLoginId(String loginId);

    Boolean existsByAccountId(AccountId accountId);

}
