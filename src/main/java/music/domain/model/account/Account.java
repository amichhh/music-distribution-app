package music.domain.model.account;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import music.domain.model.account.type.AccountStatusType;
import music.domain.model.account.type.AuthorityType;
import music.domain.shared.exception.InvalidStateTransitionException;

@Entity
@Getter
public class Account {
    @Id
    private String accountId;
    @NotBlank
    private String name;
    @NotBlank
    @JsonIgnore
    private String password;
    @Enumerated(value = EnumType.STRING)
    private AuthorityType authority;
    @Enumerated(value = EnumType.STRING)
    private AccountStatusType status;

    private Account() {
    }

    private Account(final String accountId, final String name, final String password, final AuthorityType authority,
            final AccountStatusType status) {
        this.accountId = accountId;
        this.name = name;
        this.password = password;
        this.authority = authority;
        this.status = status;
    }

    /**
     * アカウントを作成する。
     */
    public static Account create(final AccountId accountId, final String name, final EncodedPassword password,
            final AuthorityType authority) {
        return new Account(accountId.value(), name, password.value(), authority, AccountStatusType.VALID);
    }

    /**
     * アカウントを変更する。
     */
    public Account change(final String name, final EncodedPassword password) {
        return new Account(this.accountId, name, password.value(), this.authority, this.status);
    }

    /**
     * アカウント状態を有効にする。
     */
    public Account validate() {
        if (this.isValid()) {
            throw new InvalidStateTransitionException("アカウント状態は既に有効です。");
        }
        return new Account(this.accountId, this.name, this.password, this.authority, AccountStatusType.VALID);
    }

    /**
     * アカウント状態を無効にする。
     */
    public Account invalidate() {
        if (this.isInvalid()) {
            throw new InvalidStateTransitionException("アカウント状態は既に無効です。");
        }
        return new Account(this.accountId, this.name, this.password, this.authority, AccountStatusType.INVALID);
    }

    public Boolean isValid() {
        return this.status == AccountStatusType.VALID;
    }

    public Boolean isInvalid() {
        return this.status == AccountStatusType.INVALID;
    }
}
