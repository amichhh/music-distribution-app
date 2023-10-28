package music.domain.model.music;

import java.util.List;

public interface PlaylistItemRepository {

    List<PlaylistItem> search();

    PlaylistItem store(PlaylistItem item);

    PlaylistItem update(PlaylistItem item);

    void delete(Long id);

}
