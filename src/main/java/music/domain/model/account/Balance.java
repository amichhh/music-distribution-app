package music.domain.model.account;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import music.domain.model.Money;

@Entity
@Getter
public class Balance {
    @Id
    private String accountId;
    @NotNull
    @Min(0)
    private int amount;

    private Balance() {
    }

    private Balance(final String accountId, final int amount) {
        this.accountId = accountId;
        this.amount = amount;
    }

    /**
     * 残高を作成する。
     */
    public static Balance create(final AccountId accountId) {
        return new Balance(accountId.value(), 0);
    }

    /**
     * 残高を増やす。
     */
    public Balance addValue(final Money amount) {
        Money currentBalance = Money.create(this.amount);
        Money newBalance = currentBalance.add(amount);
        return new Balance(this.accountId, newBalance.amount());
    }

    /**
     * 残高を減らす。
     */
    public Balance reduceBalance(final Money amount) {
        Money currentBalance = Money.create(this.amount);
        Money newBalance = currentBalance.minus(amount);
        return new Balance(this.accountId, newBalance.amount());
    }
}
