package music.domain.model.account;

public interface MemberAccountRepository {

    MemberAccount store(MemberAccount account);

    MemberAccount update(MemberAccount account);

    MemberAccount load(AccountId accountId);

    MemberAccount loadByLoginId(String loginId);

    Boolean existsByAccountId(AccountId accountId);

}