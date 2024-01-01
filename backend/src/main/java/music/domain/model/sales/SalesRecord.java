package music.domain.model.sales;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import music.domain.model.account.AccountId;
import music.domain.model.sales.type.PaymentMethodType;
import music.domain.shared.IdGenerator;

@Entity
@Getter
public class SalesRecord {
    @Id
    private String id;
    @NotBlank
    private String accountId;
    @NotNull
    private Long musicId;
    @NotBlank
    private String companyId;
    @NotNull
    @Min(0)
    private int purchasePrice;
    @NotNull
    private LocalDateTime purchaseDate;
    @Enumerated(EnumType.STRING)
    private PaymentMethodType paymentMethod;

    private SalesRecord() {
    }

    private SalesRecord(final String id, final String accountId, final Long musicId, final String companyId,
            final int purchasePrice, final LocalDateTime purchaseDate, final PaymentMethodType paymentMethod) {
        this.id = id;
        this.accountId = accountId;
        this.musicId = musicId;
        this.companyId = companyId;
        this.purchasePrice = purchasePrice;
        this.purchaseDate = purchaseDate;
        this.paymentMethod = paymentMethod;
    }

    /** 売上記録を作成する。 */
    public static SalesRecord create(final AccountId accountId, final Long musicId, final String companyId,
            final int purchasePrice, final PaymentMethodType paymentMethod) {
        return new SalesRecord(IdGenerator.salesRecordId(), accountId.value(), musicId, companyId, purchasePrice,
                LocalDateTime.now(), paymentMethod);
    }
}