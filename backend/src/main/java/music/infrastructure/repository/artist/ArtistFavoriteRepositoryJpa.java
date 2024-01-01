package music.infrastructure.repository.artist;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import music.domain.model.account.AccountId;
import music.domain.model.artist.ArtistFavorite;
import music.domain.model.artist.ArtistFavoriteRepository;

@Repository
public interface ArtistFavoriteRepositoryJpa extends JpaRepository<ArtistFavorite, Long>, ArtistFavoriteRepository {

    default List<ArtistFavorite> searchByAccountId(AccountId accountId) {
        return findByAccountId(accountId.value());
    }

    default ArtistFavorite loadByAccountIdAndArtistId(AccountId accountId, Long artistId) {
        return findByAccountIdAndArtistId(accountId.value(), artistId);
    }

    default ArtistFavorite store(ArtistFavorite favorite) {
        return save(favorite);
    }

    default void delete(Long id) {
        deleteById(id);
    }

    default Boolean isLiked(AccountId accountId, Long artistId) {
        return existsByAccountIdAndArtistId(accountId.value(), artistId);
    }

    @Query(value = "SELECT * FROM artist_favorite af WHERE af.account_id = ?1", nativeQuery = true)
    List<ArtistFavorite> findByAccountId(String accountId);

    @Query(value = "SELECT * FROM artist_favorite af WHERE af.account_id = ?1 AND af.artist_id = ?2", nativeQuery = true)
    ArtistFavorite findByAccountIdAndArtistId(String accountId, Long artistId);

    @Query(value = "SELECT EXISTS(SELECT * FROM artist_favorite af WHERE af.account_id = ?1 AND af.artist_id = ?2)", nativeQuery = true)
    Boolean existsByAccountIdAndArtistId(String accountId, Long artistId);
}
