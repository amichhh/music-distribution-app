package music.domain.model.artist;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Entity
@Getter
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String outline;

    private Artist() {
    }

    private Artist(final String name, final String outline) {
        this.name = name;
        this.outline = outline;
    }

    private Artist(final Long id, final String name, final String outline) {
        this.name = name;
        this.outline = outline;
    }

    /**
     * アーティストを作成する。
     */
    public static Artist create(final String name, final String outline) {
        return new Artist(name, outline);
    }

    /**
     * アーティストを変更する。
     */
    public Artist change(final String name, final String outline) {
        return new Artist(this.id, name, outline);
    }
}
