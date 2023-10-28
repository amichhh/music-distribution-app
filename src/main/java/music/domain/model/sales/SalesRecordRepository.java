package music.domain.model.sales;

import java.util.List;

import music.domain.model.account.AccountId;

public interface SalesRecordRepository {

    List<SalesRecord> search();

    List<SalesRecord> searchByAccountId(AccountId accountId);

    SalesRecord store(SalesRecord record);

}