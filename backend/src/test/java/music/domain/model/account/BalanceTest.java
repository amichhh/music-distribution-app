package music.domain.model.account;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import music.domain.model.Money;

public class BalanceTest {

    Balance balance1;
    Balance balance2;

    @BeforeEach
    void setUp() {
        balance1 = Balance.create(AccountId.of("test_account_id_1"));
        balance2 = Balance.create(AccountId.of("test_account_id_2")).addValue(Money.of(1000));
    }

    @Test
    void create() {
        AccountId accountId = AccountId.of("test_account_id");

        Balance balance = Balance.create(accountId);

        assertEquals(accountId.value(), balance.getAccountId());
        assertEquals(0, balance.getAmount());
    }

    @Test
    void addValue() {
        assertEquals(0, balance1.getAmount());

        Balance added = balance1.addValue(Money.of(1000));

        assertEquals(1000, added.getAmount());
    }

    @Test
    void reduceBalance() {
        assertEquals(1000, balance2.getAmount());

        Balance reduced = balance2.reduceBalance(Money.of(100));

        assertEquals(900, reduced.getAmount());
    }
}
