package music.application.usecase.sales.business;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import music.application.dto.sales.FindSalesRecordDto;
import music.application.usecase.security.GetAuthenticationUseCase;
import music.domain.model.account.AccountId;
import music.domain.model.account.Belong;
import music.domain.model.account.BelongRepository;
import music.domain.model.sales.SalesRecord;
import music.domain.model.sales.SalesRecordRepository;

@Service
@Transactional
@AllArgsConstructor
public class FindSalesRecordUseCase {
    private final SalesRecordRepository SalesRecordRep;
    private final BelongRepository belongRep;
    private final GetAuthenticationUseCase authentication;

    /**
     * 自身の所属する企業の売上記録を検索する。
     * 
     * @return ログイン中のユーザーの所属企業に紐づく売上記録リスト
     */
    public List<SalesRecord> findSalesRecord() {
        AccountId loginUserId = authentication.accountId();
        Belong belong = belongRep.load(loginUserId);
        return SalesRecordRep.searchByCompanyId(belong.getCompanyId());
    }

    /**
     * 自身の所属する企業の日付ごとの売上記録を検索する。
     * 
     * @return ログイン中のユーザーの所属企業に紐づく日付ごとの売上記録リスト
     */
    public List<SalesRecord> findSalesRecordPerDay(final FindSalesRecordDto param) {
        return findSalesRecord().stream().filter(v -> v.getPurchaseDate().toLocalDate().isEqual(param.getDay()))
                .toList();
    }
}
