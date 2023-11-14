package music.domain.model.artist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import music.domain.model.account.AccountId;

public class ArtistFavoriteTest {

    @Test
    void create() {
        AccountId accountId = AccountId.of("test_account_id");
        Long artistId = 1L;

        ArtistFavorite favorite = ArtistFavorite.create(accountId, artistId);

        assertEquals(accountId.value(), favorite.getAccountId());
        assertEquals(artistId, favorite.getArtistId());
    }

}
