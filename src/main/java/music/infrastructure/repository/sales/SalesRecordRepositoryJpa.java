package music.infrastructure.repository.sales;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import music.domain.model.account.AccountId;
import music.domain.model.sales.SalesRecord;
import music.domain.model.sales.SalesRecordRepository;

@Repository
public interface SalesRecordRepositoryJpa extends JpaRepository<SalesRecord, String>, SalesRecordRepository {

    default List<SalesRecord> search() {
        return findAll();
    }

    default List<SalesRecord> searchByAccountId(AccountId accountId) {
        return findByAccountId(accountId.value());
    }

    default List<SalesRecord> searchByCompanyId(String companyId) {
        return findByCompanyId(companyId);
    }

    default SalesRecord store(SalesRecord record) {
        return save(record);
    }

    default Boolean isPurchased(AccountId accountId, Long musicId) {
        return existsByAccountIdAndMusicId(accountId.value(), musicId);
    }

    @Query(value = "SELECT * FROM sales_record sr WHERE sr.account_id = ?1", nativeQuery = true)
    List<SalesRecord> findByAccountId(String accountId);

    @Query(value = "SELECT * FROM sales_record sr WHERE sr.company_id = ?1", nativeQuery = true)
    List<SalesRecord> findByCompanyId(String companyId);

    @Query(value = "SELECT EXISTS(SELECT * FROM sales_record sr WHERE sr.account_id = ?1 AND sr.music_id = ?2)", nativeQuery = true)
    Boolean existsByAccountIdAndMusicId(String accountId, Long musicId);

}
