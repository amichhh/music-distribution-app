package music.domain.model.music;

import java.util.List;

public interface PlaylistItemRepository {

    List<PlaylistItem> search(Long playlistId);

    PlaylistItem store(PlaylistItem playlistItem);

    void delete(Long id);

    int itemCount(Long playlistId);

}
