package music.application.usecase.music;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import music.application.dto.music.FindMusicDto;
import music.domain.model.music.Music;
import music.domain.model.music.MusicRepository;

@Service
@Transactional
@AllArgsConstructor
public class FindMusicUseCase {
    private final MusicRepository rep;

    /**
     * タイトルで楽曲を検索する。
     * 
     * @return タイトルに検索文字列を含む楽曲リスト
     */
    public List<Music> findMusicByTitle(final FindMusicDto param) {
        return rep.searchByTitle(param.getTitle());
    }

    /**
     * アーティスト名で楽曲を検索する。
     * 
     * @return アーティスト名に検索文字列を含む楽曲リスト
     */
    public List<Music> findMusicByArtistName(final FindMusicDto param) {
        return rep.searchByArtistName(param.getArtistName());
    }

    /**
     * 人気順で楽曲を検索する。
     * 
     * @return 購入数の降順の楽曲リスト
     */
    public List<Music> findMusicOrderByPopularity() {
        return rep.searchOrderByPopularity();
    }

    /**
     * 新着順で楽曲を検索する。
     * 
     * @return リリース日の降順の楽曲リスト
     */
    public List<Music> findMusicOrderByNewest() {
        return rep.searchOrderByNewest();
    }

}
