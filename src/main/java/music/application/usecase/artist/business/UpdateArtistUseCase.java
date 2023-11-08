package music.application.usecase.artist.business;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import music.application.dto.artist.UpdateArtistDto;
import music.domain.model.artist.Artist;
import music.domain.model.artist.ArtistRepository;

@Service
@Transactional
@AllArgsConstructor
public class UpdateArtistUseCase {
    private final ArtistRepository rep;

    /**
     * アーティストを編集する。
     * 
     * @param param アーティスト編集用DTO
     * @return 編集したアーティスト
     */
    public Artist editArtist(final UpdateArtistDto param) {
        Artist current = rep.load(param.getId());
        // TODO: 同名のアーティストを許容するか
        // TODO: 同じ企業、同じ名前のアーティスト
        return rep.store(changeArtist(current, param));
    }

    /**
     * アーティストのインスタンスを生成する。
     */
    private Artist changeArtist(final Artist current, final UpdateArtistDto param) {
        return current.change(param.getName(), param.getOutline());
    }
}
