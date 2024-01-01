package music.domain.model.account;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BelongTest {

    @Test
    void create() {
        AccountId accountId = AccountId.of("test_account_id");
        String companyId = "COM-aaaa-aaaa-aaaa-aaaa";

        Belong belong = Belong.create(accountId, companyId);

        assertEquals(accountId.value(), belong.getAccountId());
        assertEquals(companyId, belong.getCompanyId());
    }

}
