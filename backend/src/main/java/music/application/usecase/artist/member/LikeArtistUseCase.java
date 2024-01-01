package music.application.usecase.artist.member;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import music.application.dto.artist.RegisterArtistFavoriteDto;
import music.application.usecase.security.GetAuthenticationUseCase;
import music.domain.model.account.AccountId;
import music.domain.model.artist.ArtistFavorite;
import music.domain.model.artist.ArtistFavoriteRepository;

@Service
@Transactional
@AllArgsConstructor
public class LikeArtistUseCase {
    private final ArtistFavoriteRepository rep;
    private final GetAuthenticationUseCase authentication;

    /**
     * アーティストをお気に入り登録する。
     * 
     * @param param アーティストお気に入り登録用DTO
     * @return 登録したアーティストのお気に入り
     */
    public ArtistFavorite likeArtist(final RegisterArtistFavoriteDto param) {
        AccountId loginUserId = authentication.accountId();
        if (rep.isLiked(loginUserId, param.getArtistId())) {
            throw new RuntimeException("アーティストは既にお気に入りに登録されています。");
        }
        return rep.store(createArtistFavorite(loginUserId, param));
    }

    /**
     * アーティストのお気に入りインスタンスを生成する。
     */
    private ArtistFavorite createArtistFavorite(final AccountId accountId, final RegisterArtistFavoriteDto param) {
        return ArtistFavorite.create(accountId, param.getArtistId());
    }
}
