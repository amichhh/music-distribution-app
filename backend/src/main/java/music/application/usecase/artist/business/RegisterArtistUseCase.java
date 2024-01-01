package music.application.usecase.artist.business;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import music.application.dto.artist.RegisterArtistDto;
import music.domain.model.artist.Artist;
import music.domain.model.artist.ArtistRepository;

@Service
@Transactional
@AllArgsConstructor
public class RegisterArtistUseCase {
    private final ArtistRepository rep;

    /**
     * アーティストを登録する。
     * 
     * @param param アーティスト登録用DTO
     * @return 登録したアーティスト
     */
    public Artist registerArtist(final RegisterArtistDto param) {
        // TODO: 同名のアーティストを許容するか
        // TODO: 同じ企業、同じ名前のアーティスト
        return rep.store(createArtist(param));
    }

    /**
     * アーティストのインスタンスを生成する。
     */
    private Artist createArtist(final RegisterArtistDto param) {
        return Artist.create(param.getName(), param.getOutline());
    }
}
