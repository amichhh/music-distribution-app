package music.domain.model.sales;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import music.domain.model.account.AccountId;
import music.domain.model.sales.type.PaymentMethodType;

public class SalesRecordTest {

    @Test
    void create() {
        AccountId accountId = AccountId.of("test_account_id");
        Long musicId = 1L;
        String companyId = "COM-aaaa-aaaa-aaaa-aaaa";
        int purchasePrice = 250;

        SalesRecord record = SalesRecord.create(accountId, musicId, companyId, purchasePrice,
                PaymentMethodType.BALANCE);

        assertEquals(accountId.value(), record.getAccountId());
        assertEquals(musicId, record.getMusicId());
        assertEquals(companyId, record.getCompanyId());
        assertEquals(purchasePrice, record.getPurchasePrice());
        assertEquals(PaymentMethodType.BALANCE, record.getPaymentMethod());
    }

}
