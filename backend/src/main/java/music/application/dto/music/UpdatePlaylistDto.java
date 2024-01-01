package music.application.dto.music;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdatePlaylistDto {
    @NotNull
    private Long id;
    @NotBlank
    private String title;
    private List<Long> items;
}
