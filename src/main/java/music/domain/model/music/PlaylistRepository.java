package music.domain.model.music;

import java.util.List;

import music.domain.model.account.AccountId;

public interface PlaylistRepository {

    List<Playlist> search(AccountId accountId);

    Playlist store(Playlist playlist);

    Playlist update(Playlist playlist);

    Boolean isDuplicatedTitle(AccountId accountId, String title);

}
