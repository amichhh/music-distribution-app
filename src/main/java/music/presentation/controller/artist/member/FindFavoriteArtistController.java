package music.presentation.controller.artist.member;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import music.application.usecase.artist.member.FindFavoriteArtistUseCase;
import music.domain.model.artist.Artist;

@RestController
@RequestMapping("/api/member/artist")
@AllArgsConstructor
public class FindFavoriteArtistController {
    private final FindFavoriteArtistUseCase useCase;

    /**
     * お気に入り登録したアーティストを検索する。
     */
    @GetMapping("/favorite")
    public List<Artist> findFavoriteArtist() {
        return useCase.findFavoriteArtist();
    }
}
