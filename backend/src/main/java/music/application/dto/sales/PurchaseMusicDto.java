package music.application.dto.sales;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PurchaseMusicDto {
    @NotNull
    private Long musicId;
    @NotBlank
    private String paymentMethod;
}
