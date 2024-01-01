package music.domain.model.sales;

import java.util.List;

import music.domain.model.account.AccountId;

public interface SalesRecordRepository {

    List<SalesRecord> search();

    List<SalesRecord> searchByAccountId(AccountId accountId);

    List<SalesRecord> searchByCompanyId(String companyId);

    SalesRecord store(SalesRecord record);

    Boolean isPurchased(AccountId accountId, Long musicId);

}