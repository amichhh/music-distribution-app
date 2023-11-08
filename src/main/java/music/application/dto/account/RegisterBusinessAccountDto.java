package music.application.dto.account;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterBusinessAccountDto {
    @NotBlank
    private String accountId;
    @NotBlank
    private String name;
    @NotBlank
    private String companyId;
    @NotBlank
    private String password;
}
