package music.domain.model.artist;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import music.domain.model.account.AccountId;

@Entity
@Getter
public class ArtistFavorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String accountId;
    @NotNull
    private Long artistId;

    private ArtistFavorite() {
    }

    private ArtistFavorite(final String accountId, final Long artistId) {
        this.accountId = accountId;
        this.artistId = artistId;
    }

    /**
     * アーティストのお気に入りを作成する。
     */
    public static ArtistFavorite create(final AccountId accountId, final Long artistId) {
        return new ArtistFavorite(accountId.value(), artistId);
    }
}
