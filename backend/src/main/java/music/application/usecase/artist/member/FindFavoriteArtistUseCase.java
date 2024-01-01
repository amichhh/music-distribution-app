package music.application.usecase.artist.member;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import music.application.usecase.security.GetAuthenticationUseCase;
import music.domain.model.account.AccountId;
import music.domain.model.artist.Artist;
import music.domain.model.artist.ArtistFavorite;
import music.domain.model.artist.ArtistFavoriteRepository;
import music.domain.model.artist.ArtistRepository;

@Service
@Transactional
@AllArgsConstructor
public class FindFavoriteArtistUseCase {
    private final ArtistRepository ArtistRep;
    private final ArtistFavoriteRepository ArtistFavoriteRep;
    private final GetAuthenticationUseCase authentication;

    /**
     * お気に入り登録したアーティストを検索する。
     * 
     * @return お気に入り登録したアーティストのリスト
     */
    public List<Artist> findFavoriteArtist() {
        AccountId loginUserId = authentication.accountId();
        List<ArtistFavorite> favorites = ArtistFavoriteRep.searchByAccountId(loginUserId);
        List<Long> artistIds = favorites.stream().map(v -> v.getArtistId()).toList();
        return ArtistRep.searchByIds(artistIds);
    }
}
