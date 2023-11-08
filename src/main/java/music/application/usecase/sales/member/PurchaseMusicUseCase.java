package music.application.usecase.sales.member;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import music.application.dto.sales.PurchaseMusicDto;
import music.application.usecase.security.GetAuthenticationUseCase;
import music.domain.model.Money;
import music.domain.model.account.AccountId;
import music.domain.model.account.Balance;
import music.domain.model.account.BalanceRepository;
import music.domain.model.music.Music;
import music.domain.model.music.MusicRepository;
import music.domain.model.sales.SalesRecord;
import music.domain.model.sales.SalesRecordRepository;
import music.domain.model.sales.type.PaymentMethodType;

@Service
@Transactional
@AllArgsConstructor
public class PurchaseMusicUseCase {
    private final SalesRecordRepository salesRecordRep;
    private final BalanceRepository balanceRep;
    private final MusicRepository musicRep;
    private final GetAuthenticationUseCase authentication;

    /**
     * 楽曲を購入する。
     */
    public SalesRecord purchaseMusic(final PurchaseMusicDto param) {
        AccountId loginUserId = authentication.accountId();
        if (salesRecordRep.isPurchased(loginUserId, param.getMusicId())) {
            throw new RuntimeException("購入済みの楽曲です。");
        }
        Balance balance = balanceRep.load(loginUserId);
        Music music = musicRep.load(param.getMusicId());
        Money currentBalance = Money.of(balance.getAmount());
        Money purchasePrice = Money.of(music.getPrice());
        if (currentBalance.isNotEnough(purchasePrice)) {
            throw new RuntimeException("残高が不足しています。");
        }
        // 残高を使用
        balanceRep.update(balance.reduceBalance(purchasePrice));
        // 購入回数を増加
        musicRep.update(music.plusPurchaseCount());
        return salesRecordRep.store(createSalesRecord(loginUserId, param));
    }

    /**
     * 売上記録のインスタンスを生成する。
     */
    private SalesRecord createSalesRecord(final AccountId accountId, final PurchaseMusicDto param) {
        Music music = musicRep.load(param.getMusicId());
        return SalesRecord.create(accountId, param.getMusicId(), music.getCompanyId(), music.getPrice(),
                PaymentMethodType.valueOf(param.getPaymentMethod()));
    }

}
