package music.infrastructure.repository.artist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import music.domain.model.account.AccountId;
import music.domain.model.artist.ArtistFavorite;
import music.domain.model.artist.ArtistFavoriteRepository;

@Repository
public interface ArtistFavoriteRepositoryJpa extends JpaRepository<ArtistFavorite, Long>, ArtistFavoriteRepository {

    default ArtistFavorite store(ArtistFavorite favorite) {
        return save(favorite);
    }

    default void delete(Long id) {
        deleteById(id);
    }

    default Boolean isLiked(AccountId accountId, Long artistId) {
        return existsByAccountIdAndArtistId(accountId.value(), artistId);
    }

    @Query(value = "SELECT EXISTS(SELECT * FROM artist_favorite af WHERE af.account_id = ?1 AND af.artist_id = ?2)", nativeQuery = true)
    Boolean existsByAccountIdAndArtistId(String accountId, Long artistId);

}