package music.domain.model.account;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import music.domain.shared.IdGenerator;

@Entity
@Getter
public class Company {
    @Id
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;

    private Company() {
    }

    private Company(final String id, final String name, final String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    /**
     * 企業を作成する。
     */
    public static Company create(final String name, final String description) {
        return new Company(IdGenerator.companyId(), name, description);
    }

    /**
     * 企業を変更する。
     */
    public Company change(final String name, final String description) {
        return new Company(this.id, name, description);
    }
}
