package music.application.dto.account;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateBusinessAccountDto {
    @NotBlank
    private final String accountId;
    @NotBlank
    private final String name;
    @NotBlank
    private final String password;
}
