package music.domain.model.music;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import music.domain.model.account.AccountId;

public class PlaylistTest {

    @Test
    void create() {
        AccountId accountId = AccountId.of("test_account_id");
        String title = "テストプレイリストタイトル";

        Playlist playlist = Playlist.create(accountId, title);

        assertEquals(accountId.value(), playlist.getAccountId());
        assertEquals(title, playlist.getTitle());
    }

}
