package music.domain.model.music;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import music.domain.model.account.AccountId;

@Entity
@Data
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String accountId;
    @NotBlank
    private String title;

    private Playlist() {
    }

    private Playlist(final String accountId, final String title) {
        this.accountId = accountId;
        this.title = title;
    }

    /** プレイリストを作成する。 */
    public static Playlist create(final AccountId accountId, final String title) {
        return new Playlist(accountId.value(), title);
    }
}