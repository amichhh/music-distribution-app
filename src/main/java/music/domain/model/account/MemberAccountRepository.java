package music.domain.model.account;

public interface MemberAccountRepository {

    MemberAccount save(MemberAccount account);

    MemberAccount load(AccountId accountId);

    MemberAccount loadByLoginId(String loginId);

    Boolean existsByAccountId(AccountId accountId);

}