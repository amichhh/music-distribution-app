package music.application.dto.music;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdatePlaylistItemDto {
    @NotNull
    private Long playlistId;
    @NotNull
    private Long musicId;
    @NotNull
    private int sortOrder;
}
