package music.domain.model.music;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import music.domain.model.music.type.MusicStatusType;

public class MusicTest {

    Music music1;
    Music music2;

    @BeforeEach
    void setUp() {
        music1 = Music.create(
                "テスト楽曲タイトル1",
                1L,
                "COM-1111-1111-1111-1111",
                250,
                LocalDate.of(2023, 11, 1));
        music2 = Music.create(
                "テスト楽曲タイトル2",
                2L,
                "COM-2222-2222-2222-2222",
                250,
                LocalDate.of(2024, 11, 1));
    }

    @Test
    void create() {
        String title = "テスト楽曲タイトル";
        Long artistId = 0L;
        String companyId = "COM-aaaa-aaaa-aaaa-aaaa";
        int price = 250;
        LocalDate day = LocalDate.of(2023, 10, 10);

        Music music = Music.create(title, artistId, companyId, price, day);

        assertEquals(title, music.getTitle());
        assertEquals(artistId, music.getArtistId());
        assertEquals(companyId, music.getCompanyId());
        assertEquals(price, music.getPrice());
        assertEquals(day, music.getReleaseDay());
        assertEquals(0, music.getPurchaseCount());
        assertEquals(MusicStatusType.AVAILABLE, music.getStatus());
    }

    @Test
    void enable() {
        assertTrue(music1.isAvailable());
        assertTrue(music2.isUnavailable());

        assertThrows(RuntimeException.class, () -> music1.enable());

        Music enabled = music2.enable();

        assertTrue(enabled.isAvailable());
    }

    @Test
    void plusPurchaseCount() {
        assertEquals(0, music1.getPurchaseCount());

        Music increased = music1.plusPurchaseCount();

        assertEquals(1, increased.getPurchaseCount());
    }

}
