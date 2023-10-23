package music.domain.model.account;

public class AccountId {
    private static final Integer MAX_LENGTH = 30;
    final String value;

    private AccountId(final String accountId) {
        if (MAX_LENGTH < accountId.length()) {
            throw new IllegalArgumentException(String.format("アカウントIDが%s文字以内ではありません。", MAX_LENGTH));
        }
        this.value = accountId;
    }

    public static AccountId create(final String accountId) {
        return new AccountId(accountId);
    }

    public String value() {
        return this.value;
    }
}