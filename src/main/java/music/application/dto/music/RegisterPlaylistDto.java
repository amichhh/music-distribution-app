package music.application.dto.music;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterPlaylistDto {
    @NotBlank
    private String title;
    private List<Long> items;
}
