package music.domain.shared;

import java.util.UUID;

public class IdGenerator {
    private static final String COMPANY_PREFIX = "COM-";
    private static final String SALES_RECORD_PREFIX = "SAL-";

    private static String uuid() {
        return UUID.randomUUID().toString();
    }

    public static String companyId() {
        return COMPANY_PREFIX + uuid();
    }

    public static String salesRecordId() {
        return SALES_RECORD_PREFIX + uuid();
    }
}
