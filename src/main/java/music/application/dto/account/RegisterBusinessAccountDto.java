package music.application.dto.account;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterBusinessAccountDto {
    @NotBlank
    private final String accountId;
    @NotBlank
    private final String name;
    @NotBlank
    private final String companyId;
    @NotBlank
    private final String password;
}
