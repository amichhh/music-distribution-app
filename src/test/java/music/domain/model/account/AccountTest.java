package music.domain.model.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import music.domain.model.account.type.AuthorityType;

public class AccountTest {

    Account account1;
    Account account2;

    @BeforeEach
    void setUp() {
        account1 = Account.create(
                AccountId.of("test_member_id_1"),
                "test_member_name_1",
                EncodedPassword.of("TestPass1"),
                AuthorityType.MEMBER);
        account2 = Account.create(
                AccountId.of("test_member_id_2"),
                "test_member_name_2",
                EncodedPassword.of("TestPass2"),
                AuthorityType.MEMBER);
        account2 = account2.invalidate();
    }

    @Test
    void create() {
        AccountId accountId = AccountId.of("test_member_id");
        String name = "test_member_name";
        EncodedPassword password = EncodedPassword.of("TestPass0");

        Account account = Account.create(accountId, name, password, AuthorityType.MEMBER);

        assertEquals(accountId.value(), account.getAccountId());
        assertEquals(name, account.getName());
        assertEquals(password.value(), account.getPassword());
        assertEquals(AuthorityType.MEMBER, account.getAuthority());
        assertTrue(account.isValid());
    }

    @Test
    void validate() {
        assertTrue(account1.isValid());
        assertTrue(account2.isInvalid());

        assertThrows(RuntimeException.class, () -> account1.validate());

        Account validated = account2.validate();

        assertTrue(account1.isValid());
        assertTrue(validated.isValid());
    }

    @Test
    void invalidate() {
        assertTrue(account1.isValid());
        assertTrue(account2.isInvalid());

        assertThrows(RuntimeException.class, () -> account2.invalidate());

        Account invalidated = account1.invalidate();

        assertTrue(invalidated.isInvalid());
        assertTrue(account2.isInvalid());
    }

}
