package music.application.dto.artist;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterArtistFavoriteDto {
    @NotNull
    private final Long artistId;
}
