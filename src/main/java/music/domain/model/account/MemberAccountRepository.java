package music.domain.model.account;

import java.util.List;

public interface MemberAccountRepository {

    List<MemberAccount> search();

    MemberAccount store(MemberAccount account);

    MemberAccount update(MemberAccount account);

    MemberAccount load(AccountId accountId);

    MemberAccount loadByLoginId(String loginId);

    Boolean existsByAccountId(AccountId accountId);

}