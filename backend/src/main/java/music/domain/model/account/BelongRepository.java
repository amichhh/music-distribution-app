package music.domain.model.account;

public interface BelongRepository {

    Belong load(AccountId accountId);

    Belong store(Belong belong);

}
