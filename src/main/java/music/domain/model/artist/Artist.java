package music.domain.model.artist;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import music.domain.shared.IdGenerator;

@Entity
public class Artist {
    @Id
    private String artistId;
    @NotBlank
    private String name;
    @NotBlank
    private String outline;

    private Artist() {
    }

    private Artist(final String artistId, final String name, final String outline) {
        this.artistId = artistId;
        this.name = name;
        this.outline = outline;
    }

    /**
     * アーティストを作成する。
     */
    public static Artist create(final String artistId, final String name, final String outline) {
        return new Artist(IdGenerator.genarateArtistId(), name, outline);
    }

    /**
     * アーティストを変更する。
     */
    public Artist change(final String name, final String outline) {
        return new Artist(this.artistId, name, outline);
    }
}