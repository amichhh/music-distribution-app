package music.infrastructure.repository.music;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import music.domain.model.account.AccountId;
import music.domain.model.music.Playlist;
import music.domain.model.music.PlaylistRepository;

@Repository
public interface PlaylistRepositoryJpa extends JpaRepository<Playlist, Long>, PlaylistRepository {

    default List<Playlist> search(AccountId accountId) {
        return findByAccountId(accountId.value());
    }

    default Playlist store(Playlist playlist) {
        return save(playlist);
    }

    default Playlist update(Playlist playlist) {
        return save(playlist);
    }

    default Boolean isDuplicatedTitle(AccountId accountId, String title) {
        return existsByAccountIdAndTitle(accountId.value(), title);
    }

    @Query(value = "SELECT * FROM playlist p WHERE p.account_id = ?1", nativeQuery = true)
    List<Playlist> findByAccountId(String accountId);

    @Query(value = "SELECT EXISTS(SELECT * FROM playlist p WHERE p.account_id = ?1 AND p.title = ?2)", nativeQuery = true)
    Boolean existsByAccountIdAndTitle(String accountId, String title);
}
