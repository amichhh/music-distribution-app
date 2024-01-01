package music.application.usecase.artist;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import music.application.dto.artist.FindArtistDto;
import music.domain.model.artist.Artist;
import music.domain.model.artist.ArtistRepository;

@Service
@Transactional
@AllArgsConstructor
public class FindArtistUseCase {
    private final ArtistRepository rep;

    /**
     * アーティスト名でアーティストを検索する。
     * 
     * @return 名前に検索文字列を含むアーティストのリスト
     */
    public List<Artist> findArtistByName(final FindArtistDto param) {
        return rep.searchByName(param.getName());
    }
}
