package music.domain.model.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompanyTest {

    Company company1;

    @BeforeEach
    void setUp() {
        company1 = Company.create("テスト企業名1", "テスト企業備考1");
    }

    @Test
    void create() {
        String name = "テスト企業名";
        String description = "テスト企業備考";

        Company company = Company.create(name, description);

        assertEquals(name, company.getName());
        assertEquals(description, company.getDescription());
    }

    @Test
    void change() {
        String name = "テスト変更企業名";
        String description = "テスト変更企業備考";

        assertNotEquals(name, company1.getName());
        assertNotEquals(description, company1.getDescription());

        Company changed = company1.change(name, description);

        assertEquals(name, changed.getName());
        assertEquals(description, changed.getDescription());
    }

}
