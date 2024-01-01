package music.domain.model.music;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PlaylistItemTest {

    @Test
    void create() {
        Long playlistId = 1L;
        Long musicId = 1L;
        int sortOrder = 1;

        PlaylistItem playlistItem = PlaylistItem.create(playlistId, musicId, sortOrder);

        assertEquals(playlistId, playlistItem.getPlaylistId());
        assertEquals(musicId, playlistItem.getMusicId());
        assertEquals(sortOrder, playlistItem.getSortOrder());
    }

}
