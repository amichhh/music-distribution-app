package music.application.dto.artist;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterArtistDto {
    @NotBlank
    private String name;
    @NotBlank
    private String outline;
}
