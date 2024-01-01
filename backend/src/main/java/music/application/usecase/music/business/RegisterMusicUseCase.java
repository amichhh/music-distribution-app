package music.application.usecase.music.business;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import music.application.dto.music.RegisterMusicDto;
import music.application.usecase.security.GetAuthenticationUseCase;
import music.domain.model.account.AccountId;
import music.domain.model.account.BelongRepository;
import music.domain.model.music.Music;
import music.domain.model.music.MusicRepository;

@Service
@Transactional
@AllArgsConstructor
public class RegisterMusicUseCase {
    private final MusicRepository MusicRep;
    private final BelongRepository BelongRep;
    private final GetAuthenticationUseCase authentication;

    /**
     * 楽曲を登録する。
     * 
     * @param param 楽曲登録用DTO
     * @return 登録した楽曲
     */
    public Music registerMusic(final RegisterMusicDto param) {
        AccountId loginUserId = authentication.accountId();
        // TODO: 同じアーティストIDで同じタイトル
        return MusicRep.store(createMusic(loginUserId, param));
    }

    /**
     * 楽曲のインスタンスを生成する。
     */
    private Music createMusic(final AccountId accountId, final RegisterMusicDto param) {
        return Music.create(param.getTitle(), param.getArtistId(), BelongRep.load(accountId).getCompanyId(),
                param.getPrice(), param.getReleaseDay());
    }

}
