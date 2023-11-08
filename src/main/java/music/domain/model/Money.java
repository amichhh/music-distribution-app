package music.domain.model;

public class Money {
    private final int amount;

    private Money(final int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("金額が0円より小さい数字です。");
        }
        this.amount = amount;
    }

    public static Money of(final int amount) {
        return new Money(amount);
    }

    public Money add(final Money add) {
        int added = this.amount + add.amount;
        return new Money(added);
    }

    public Money minus(final Money minus) {
        int minused = this.amount - minus.amount;
        return new Money(minused);
    }

    public Boolean isNotEnough(final Money compare) {
        return this.amount < compare.amount;
    }

    public int amount() {
        return this.amount;
    }
}
