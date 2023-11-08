package music.application.dto.artist;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterArtistFavoriteDto {
    @NotNull
    private Long artistId;
}
