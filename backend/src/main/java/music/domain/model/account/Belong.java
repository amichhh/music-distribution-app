package music.domain.model.account;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Entity
@Getter
public class Belong {
    @Id
    private String accountId;
    @NotBlank
    private String companyId;

    private Belong() {
    }

    private Belong(final String accountId, final String companyId) {
        this.accountId = accountId;
        this.companyId = companyId;
    }

    /**
     * 所属先を作成する。
     */
    public static Belong create(final AccountId accountId, final String companyId) {
        return new Belong(accountId.value(), companyId);
    }
}
