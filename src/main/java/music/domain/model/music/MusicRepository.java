package music.domain.model.music;

import java.util.List;

public interface MusicRepository {

    Music load(Long id);

    List<Music> search();

    Music store(Music music);

    Music update(Music music);

    List<Music> searchByTitle(String title);

    List<Music> searchByArtistName(String artistName);

    List<Music> searchOrderByPopularity();

    List<Music> searchOrderByNewest();

}