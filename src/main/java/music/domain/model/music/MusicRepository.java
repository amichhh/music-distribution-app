package music.domain.model.music;

import java.util.List;

public interface MusicRepository {

    Music store(Music music);

    Music update(Music music);

    Music load(String musicId);

    List<Music> searchByTitle(String title);

    List<Music> searchByArtistName(String artistName);

    List<Music> searchOrderByPopularity();

    List<Music> searchOrderByNewest();

}