package music.application.dto.artist;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateArtistDto {
    @NotBlank
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String outline;
}
