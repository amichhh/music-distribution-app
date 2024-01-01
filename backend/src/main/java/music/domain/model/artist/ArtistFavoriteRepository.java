package music.domain.model.artist;

import java.util.List;

import music.domain.model.account.AccountId;

public interface ArtistFavoriteRepository {

    List<ArtistFavorite> searchByAccountId(AccountId accountId);

    ArtistFavorite loadByAccountIdAndArtistId(AccountId accountId, Long artistId);

    ArtistFavorite store(ArtistFavorite favorite);

    void delete(Long id);

    Boolean isLiked(AccountId accountId, Long artistId);

}
