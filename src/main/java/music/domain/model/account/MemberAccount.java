package music.domain.model.account;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
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
    @Enumerated(value = EnumType.STRING)
    private AuthorityType authority;
    @Enumerated(value = EnumType.STRING)
    private AccountStatusType accountStatus;

    private MemberAccount() {
    }

    private MemberAccount(final AccountId accountId, final String name, final EncodedPassword password,
            final AuthorityType authority, final AccountStatusType accountStatus) {
        this.accountId = accountId.value();
        this.name = name;
        this.password = password.value();
        this.authority = authority;
        this.accountStatus = accountStatus;
    }

    /**
     * メンバーアカウントを作成する。
     */
    public static MemberAccount create(final AccountId accountId, final String name, final EncodedPassword password) {
        return new MemberAccount(accountId, name, password, AuthorityType.MEMBER, AccountStatusType.VALID);
    }

    /**
     * メンバーアカウントを変更する。
     */
    public MemberAccount change(final String name, final EncodedPassword password,
            final AuthorityType authority, final AccountStatusType accountStatus) {
        return new MemberAccount(AccountId.create(this.accountId), name, password, authority, accountStatus);
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

    public Boolean isValid() {
        return this.accountStatus == AccountStatusType.VALID;
    }

    public Boolean isInvalid() {
        return this.accountStatus == AccountStatusType.INVALID;
    }

    public String accountId() {
        return this.accountId;
    }

    public String name() {
        return this.name;
    }

    public String password() {
        return this.password;
    }

    public AuthorityType authority() {
        return this.authority;
    }

    public AccountStatusType status() {
        return this.accountStatus;
    }
}