package music.infrastructure.repository.sales;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import music.domain.model.account.AccountId;
import music.domain.model.sales.SalesRecord;
import music.domain.model.sales.SalesRecordRepository;

public interface SalesRecordRepositoryJpa extends JpaRepository<SalesRecord, String>, SalesRecordRepository {

    default List<SalesRecord> search() {
        return findAll();
    }

    default List<SalesRecord> searchByAccountId(AccountId accountId) {
        return findByAccountId(accountId.value());
    }

    default SalesRecord store(SalesRecord record) {
        return save(record);
    }

    @Query(value = "SELECT * FROM sales_record sr WHERE sr.account_id = ?1", nativeQuery = true)
    List<SalesRecord> findByAccountId(String accountId);

}