package music.application.usecase.music.member;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import music.application.usecase.security.GetAuthenticationUseCase;
import music.domain.model.account.AccountId;
import music.domain.model.music.Playlist;
import music.domain.model.music.PlaylistRepository;

@Service
@Transactional
@AllArgsConstructor
public class FindPlaylistUseCase {
    private final PlaylistRepository rep;
    private final GetAuthenticationUseCase authentication;

    /**
     * 自身が登録したプレイリストを検索する。
     * 
     * @return ログイン中のユーザーに紐づくプレイリスト
     */
    public List<Playlist> findPlaylist() {
        AccountId loginUserId = authentication.accountId();
        return rep.search(loginUserId);
    }
}
