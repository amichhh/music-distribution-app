package music.domain.model.artist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArtistTest {

    Artist artist1;

    @BeforeEach
    void setUp() {
        artist1 = Artist.create("テストアーティスト名1", "テストアーティスト概要1");
    }

    @Test
    void create() {
        String name = "テストアーティスト名";
        String outline = "テストアーティスト概要";

        Artist artist = Artist.create(name, outline);

        assertEquals(name, artist.getName());
        assertEquals(outline, artist.getOutline());
    }

    @Test
    void change() {
        String name = "テスト変更アーティスト名";
        String outline = "テスト変更アーティスト概要";

        assertNotEquals(name, artist1.getName());
        assertNotEquals(outline, artist1.getOutline());

        Artist changed = artist1.change(name, outline);

        assertEquals(name, changed.getName());
        assertEquals(outline, changed.getOutline());
    }

}
