package music.application.dto.account;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterMemberAccountDto {
    @NotBlank
    private String accountId;
    @NotBlank
    private String name;
    @NotBlank
    private String password;
}