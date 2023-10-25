package music.domain.model.sales;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import music.domain.model.account.AccountId;

@Entity
public class SalesRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String accountId;
    @NotBlank
    private String musicId;
    @NotBlank
    @Min(0)
    private int purchasePrice;
    @NotBlank
    private LocalDateTime purchaseDate;
    @NotBlank
    private String paymentMethod;

    private SalesRecord() {
    }

    private SalesRecord(final AccountId accountId, final String musicId, final int purchasePrice,
            final LocalDateTime purchaseDate,
            final String paymentMethod) {
        this.accountId = accountId.value();
        this.musicId = musicId;
        this.purchasePrice = purchasePrice;
        this.purchaseDate = purchaseDate;
        this.paymentMethod = paymentMethod;
    }

    /**
     * 売上記録を作成する。
     */
    public static SalesRecord create(final AccountId accountId, final String musicId, final int purchasePrice,
            final String paymentMethod) {
        return new SalesRecord(accountId, musicId, purchasePrice, LocalDateTime.now(), paymentMethod);
    }

}