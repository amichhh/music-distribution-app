package music.domain.model.account;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import music.domain.model.account.type.AccountStatusType;
import music.domain.model.account.type.AuthorityType;

@Entity
public class MemberAccount {
    @Id
    private String accountId;
    @NotBlank
    private String name;
    @NotBlank
    private String password;
    @Enumerated
    private AuthorityType authority;
    @Enumerated
    private AccountStatusType accountStatus;

    private MemberAccount(final AccountId accountId, final String name, final EncodedPassword password) {
        this.accountId = accountId.value();
        this.name = name;
        this.password = password.value();
        this.authority = AuthorityType.MEMBER;
        this.accountStatus = AccountStatusType.VALID;
    }

    /** メンバーアカウントを作成する。 */
    public static MemberAccount create(final AccountId accountId, final String name, final EncodedPassword password) {
        return new MemberAccount(accountId, name, password);
    }

    /** アカウント状態を有効にする。 */
    void validate() {
        if (this.isValid()) {
            throw new RuntimeException("アカウント状態は既に有効です。");
        }
        this.accountStatus = AccountStatusType.VALID;
    }

    /** アカウント状態を無効にする。 */
    void invalidate() {
        if (this.isInvalid()) {
            throw new RuntimeException("アカウント状態は既に無効です。");
        }
        this.accountStatus = AccountStatusType.INVALID;
    }

    Boolean isAdmin() {
        return this.authority == AuthorityType.ADMIN;
    }

    Boolean isPrivilege() {
        return this.authority == AuthorityType.PRIVILEGE;
    }

    public Boolean isValid() {
        return this.accountStatus == AccountStatusType.VALID;
    }

    public Boolean isInvalid() {
        return this.accountStatus == AccountStatusType.INVALID;
    }

    public String accountId() {
        return this.accountId;
    }

    public String password() {
        return this.password;
    }

    public AuthorityType authority() {
        return this.authority;
    }
}
