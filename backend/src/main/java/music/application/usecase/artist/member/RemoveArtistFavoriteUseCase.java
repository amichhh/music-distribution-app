package music.application.usecase.artist.member;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import music.application.dto.artist.RemoveArtistFavoriteDto;
import music.application.usecase.security.GetAuthenticationUseCase;
import music.domain.model.account.AccountId;
import music.domain.model.artist.ArtistFavorite;
import music.domain.model.artist.ArtistFavoriteRepository;

@Service
@Transactional
@AllArgsConstructor
public class RemoveArtistFavoriteUseCase {
    private final ArtistFavoriteRepository rep;
    private final GetAuthenticationUseCase authentication;

    /**
     * アーティストのお気に入り登録を解除する。
     * 
     * @param param アーティストお気に入り登録解除用DTO
     */
    public void deleteArtistFavorite(final RemoveArtistFavoriteDto param) {
        AccountId loginUserId = authentication.accountId();
        if (!rep.isLiked(loginUserId, param.getArtistId())) {
            throw new RuntimeException("お気に入りに登録されていないアーティストです。");
        }
        ArtistFavorite favorite = rep.loadByAccountIdAndArtistId(loginUserId, param.getArtistId());
        rep.delete(favorite.getId());
    }

}
