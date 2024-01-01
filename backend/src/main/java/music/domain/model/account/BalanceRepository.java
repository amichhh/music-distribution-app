package music.domain.model.account;

public interface BalanceRepository {

    Balance load(AccountId accountId);

    Balance store(Balance balance);

    Balance update(Balance balance);

}
