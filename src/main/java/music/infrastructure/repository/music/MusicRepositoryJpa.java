package music.infrastructure.repository.music;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import music.domain.model.music.Music;
import music.domain.model.music.MusicRepository;

@Repository
public interface MusicRepositoryJpa extends JpaRepository<Music, Long>, MusicRepository {

    default Music load(Long id) {
        return findById(id).orElseThrow(() -> new RuntimeException("楽曲データが見つかりませんでした。"));
    }

    default List<Music> search() {
        return findAll();
    }

    default Music store(Music music) {
        return save(music);
    }

    default Music update(Music music) {
        return save(music);
    }

    default List<Music> searchByTitle(String title) {
        return findByTitle(title);
    }

    default List<Music> searchByArtistName(String artistName) {
        return findByArtistName(artistName);
    }

    default List<Music> searchOrderByPopularity() {
        return findOrderByPopularity();
    }

    default List<Music> searchOrderByNewest() {
        return findOrderByNewest();
    }

    @Query(value = "SELECT * FROM music m WHERE m.title LIKE %:title%", nativeQuery = true)
    List<Music> findByTitle(String title);

    @Query(value = "SELECT * FROM music m INNER JOIN artist a ON m.artist_id = a.id WHERE a.name LIKE %:artistName%", nativeQuery = true)
    List<Music> findByArtistName(String artistName);

    @Query(value = "SELECT * FROM music m ORDER BY m.purchase_count DESC", nativeQuery = true)
    List<Music> findOrderByPopularity();

    @Query(value = "SELECT * FROM music m ORDER BY m.release_day DESC", nativeQuery = true)
    List<Music> findOrderByNewest();

}