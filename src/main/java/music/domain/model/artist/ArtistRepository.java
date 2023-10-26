package music.domain.model.artist;

import java.util.List;

public interface ArtistRepository {

    List<Artist> searchByName(String name);

    Artist store(Artist artist);

}