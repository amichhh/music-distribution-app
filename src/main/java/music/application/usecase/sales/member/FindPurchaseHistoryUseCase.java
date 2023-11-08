package music.application.usecase.sales.member;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import music.application.usecase.security.GetAuthenticationUseCase;
import music.domain.model.account.AccountId;
import music.domain.model.sales.SalesRecord;
import music.domain.model.sales.SalesRecordRepository;

@Service
@Transactional
@AllArgsConstructor
public class FindPurchaseHistoryUseCase {
    private final SalesRecordRepository rep;
    private final GetAuthenticationUseCase authentication;

    /**
     * 自身の購入履歴を検索する。
     * 
     * @return ログイン中のユーザーに紐づく購入履歴
     */
    public List<SalesRecord> findPurchaseHistory() {
        AccountId loginUserId = authentication.accountId();
        return rep.searchByAccountId(loginUserId);
    }
}
