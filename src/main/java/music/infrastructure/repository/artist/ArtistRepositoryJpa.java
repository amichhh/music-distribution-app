package music.infrastructure.repository.artist;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import music.domain.model.artist.Artist;
import music.domain.model.artist.ArtistRepository;
import music.infrastructure.shared.exception.DataNotFoundException;

@Repository
public interface ArtistRepositoryJpa extends JpaRepository<Artist, Long>, ArtistRepository {

    default Artist load(Long id) {
        return findById(id).orElseThrow(() -> new DataNotFoundException("アーティストのデータが見つかりませんでした。"));
    }

    default List<Artist> searchByIds(List<Long> ids) {
        return findAll().stream().filter(v -> ids.contains(v.getId())).toList();
    }

    default List<Artist> searchByName(String name) {
        return findByName(name);
    }

    default Artist store(Artist artist) {
        return save(artist);
    }

    @Query(value = "SELECT * FROM artist a WHERE a.name LIKE %?1% ", nativeQuery = true)
    List<Artist> findByName(String name);
}
