package music.domain.model.artist;

import music.domain.model.account.AccountId;

public interface ArtistFavoriteRepository {

    ArtistFavorite store(ArtistFavorite favorite);

    void delete(Long id);

    Boolean isLiked(AccountId accountId, Long artistId);

}