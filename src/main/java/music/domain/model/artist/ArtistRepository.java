package music.domain.model.artist;

import java.util.List;

public interface ArtistRepository {

    Artist load(Long id);

    List<Artist> searchByIds(List<Long> ids);

    List<Artist> searchByName(String name);

    Artist store(Artist artist);

}
