package music.domain.model.music;

public interface PlaylistRepository {

    Playlist store(Playlist playlist);

    Playlist update(Playlist playlist);

    void delete(Long id);

}
