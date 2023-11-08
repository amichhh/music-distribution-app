package music.application.dto.music;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterPlaylistItemDto {
    @NotNull
    private Long playlistId;
    @NotNull
    private Long musicId;
}
