package music.domain.model.music;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import music.domain.model.account.AccountId;

@Entity
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playlistId;
    @NotBlank
    private String title;
    @NotBlank
    private String accountId;

    private Playlist() {
    }

    private Playlist(final String title, final AccountId accountId) {
        this.title = title;
        this.accountId = accountId.value();
    }

    /** プレイリストを作成する。 */
    public Playlist create(final String title, final AccountId accountId) {
        return new Playlist(title, accountId);
    }
}