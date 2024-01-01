package music.application.usecase.music.member;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import music.application.dto.music.RegisterPlaylistDto;
import music.application.dto.music.RegisterPlaylistItemDto;
import music.application.usecase.security.GetAuthenticationUseCase;
import music.domain.model.account.AccountId;
import music.domain.model.music.Playlist;
import music.domain.model.music.PlaylistRepository;

@Service
@Transactional
@AllArgsConstructor
public class RegisterPlaylistUseCase {
    private final PlaylistRepository rep;
    private final RegisterPlaylistItemUseCase useCase;
    private final GetAuthenticationUseCase authentication;

    /**
     * プレイリストを登録する。
     * 
     * @param param プレイリスト登録用DTO
     * @return 登録したプレイリスト
     */
    public Playlist registerPlaylist(final RegisterPlaylistDto param) {
        AccountId loginUserId = authentication.accountId();
        if (rep.isDuplicatedTitle(loginUserId, param.getTitle())) {
            throw new RuntimeException("同じタイトルのプレイリストが登録済みです。");
        }
        Playlist playlist = rep.store(createPlaylist(loginUserId, param));
        Long playlistId = playlist.getId();
        // プレイリストアイテムの登録
        param.getItems().stream().forEach(v -> {
            useCase.registerPlaylistItem(new RegisterPlaylistItemDto(playlistId, v));
        });
        return playlist;
    }

    /**
     * プレイリストのインスタンスを生成する。
     */
    private Playlist createPlaylist(final AccountId accountId, final RegisterPlaylistDto param) {
        return Playlist.create(accountId, param.getTitle());
    }
}
