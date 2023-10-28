package music.domain.model.account;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import music.domain.model.Money;
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
    @NotNull
    @Min(0)
    private int balance;
    @Enumerated(value = EnumType.STRING)
    private AuthorityType authority;
    @Enumerated(value = EnumType.STRING)
    private AccountStatusType accountStatus;

    private MemberAccount() {
    }

    private MemberAccount(final String accountId, final String name, final String password, final int balance,
            final AuthorityType authority, final AccountStatusType accountStatus) {
        this.accountId = accountId;
        this.name = name;
        this.password = password;
        this.authority = authority;
        this.accountStatus = accountStatus;
        this.balance = balance;
    }

    /**
     * メンバーアカウントを作成する。
     */
    public static MemberAccount create(final AccountId accountId, final String name, final EncodedPassword password) {
        return new MemberAccount(accountId.value(), name, password.value(), 0, AuthorityType.MEMBER,
                AccountStatusType.VALID);
    }

    /**
     * メンバーアカウントを変更する。
     */
    public MemberAccount change(final String name, final EncodedPassword password) {
        return new MemberAccount(this.accountId, name, password.value(), this.balance, this.authority,
                this.accountStatus);
    }

    /**
     * 残高を増やす。
     */
    public MemberAccount addValue(final Money amount) {
        Money current = new Money(this.balance);
        Money newAmount = current.add(amount);
        this.balance = newAmount.amount();
        return this;
    }

    /**
     * 残高を減らす。
     */
    public MemberAccount reduceBalance(final Money amount) {
        Money current = new Money(this.balance);
        Money newAmount = current.minus(amount);
        this.balance = newAmount.amount();
        return this;
    }

    /**
     * アカウント状態を有効にする。
     */
    public MemberAccount validate() {
        if (this.isValid()) {
            throw new RuntimeException("アカウント状態は既に有効です。");
        }
        this.accountStatus = AccountStatusType.VALID;
        return this;
    }

    /**
     * アカウント状態を無効にする。
     */
    public MemberAccount invalidate() {
        if (this.isInvalid()) {
            throw new RuntimeException("アカウント状態は既に無効です。");
        }
        this.accountStatus = AccountStatusType.INVALID;
        return this;
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

    public int balance() {
        return this.balance;
    }
}