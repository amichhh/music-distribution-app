package music.domain.model.music;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class PlaylistItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Long playlistId;
    @NotNull
    private Long musicId;
    @NotNull
    @Min(1)
    private int sortOrder;

    private PlaylistItem(final Long playlistId, final Long musicId, final int sortOrder) {
        this.playlistId = playlistId;
        this.musicId = musicId;
        this.sortOrder = sortOrder;
    }

    /** プレイリストアイテムを作成する。 */
    PlaylistItem register(final Long playlistId, final Long musicId, final int sortOrder) {
        return new PlaylistItem(playlistId, musicId, sortOrder);
    }
}