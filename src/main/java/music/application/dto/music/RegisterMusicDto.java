package music.application.dto.music;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterMusicDto {
    @NotBlank
    private String title;
    @NotNull
    private Long artistId;
    @NotNull
    @Min(0)
    private int price;
    private LocalDate releaseDay;
}
