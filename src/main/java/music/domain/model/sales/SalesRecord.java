package music.domain.model.sales;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import music.domain.model.account.AccountId;
import music.domain.shared.IdGenerator;

@Entity
@Data
public class SalesRecord {
    @Id
    private String id;
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

    private SalesRecord(final String id, final AccountId accountId, final String musicId, final int purchasePrice,
            final String paymentMethod) {
        this.id = id;
        this.accountId = accountId.value();
        this.musicId = musicId;
        this.purchasePrice = purchasePrice;
        this.purchaseDate = LocalDateTime.now();
        this.paymentMethod = paymentMethod;
    }

    /** 売上記録を作成する。 */
    public static SalesRecord create(final AccountId accountId, final String musicId, final int purchasePrice,
            final String paymentMethod) {
        return new SalesRecord(IdGenerator.generate(), accountId, musicId, purchasePrice, paymentMethod);
    }
}