package music.presentation.controller.music;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import music.application.dto.music.FindMusicDto;
import music.application.usecase.music.FindMusicUseCase;
import music.domain.model.music.Music;

@RestController
@RequestMapping("/api/music")
@AllArgsConstructor
public class FindMusicController {
    private final FindMusicUseCase useCase;

    /**
     * タイトルで楽曲を検索する。
     */
    @GetMapping("/title")
    public List<Music> findMusicByTitle(final FindMusicDto param) {
        return useCase.findMusicByTitle(param);
    }

    /**
     * アーティスト名で楽曲を検索する。
     */
    @GetMapping("/artist")
    public List<Music> findMusicByArtistName(final FindMusicDto param) {
        return useCase.findMusicByArtistName(param);
    }

    /**
     * 人気順で楽曲を検索する。
     */
    @GetMapping("/popular")
    public List<Music> findMusicOrderByPopularity() {
        return useCase.findMusicOrderByPopularity();
    }

    /**
     * 新着順で楽曲を検索する。
     */
    @GetMapping("/new")
    public List<Music> findMusicOrderByNewest() {
        return useCase.findMusicOrderByNewest();
    }
}
