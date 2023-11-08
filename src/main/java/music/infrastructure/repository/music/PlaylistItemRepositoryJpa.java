package music.infrastructure.repository.music;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import music.domain.model.music.PlaylistItem;
import music.domain.model.music.PlaylistItemRepository;

@Repository
public interface PlaylistItemRepositoryJpa extends JpaRepository<PlaylistItem, Long>, PlaylistItemRepository {

    default List<PlaylistItem> search(Long playlistId) {
        return findByPlaylistId(playlistId);
    }

    default PlaylistItem store(PlaylistItem playlistiItem) {
        return save(playlistiItem);
    }

    default void delete(Long id) {
        deleteById(id);
    }

    default int itemCount(Long playlistId) {
        return findByPlaylistId(playlistId).size();
    }

    @Query(value = "SELECT * FROM playlist_item pi WHERE pi.playlist_id = ?1 ORDER BY pi.sort_order", nativeQuery = true)
    List<PlaylistItem> findByPlaylistId(Long playlistId);

}
