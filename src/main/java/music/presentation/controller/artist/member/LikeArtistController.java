package music.presentation.controller.artist.member;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import music.application.dto.artist.RegisterArtistFavoriteDto;
import music.application.usecase.artist.member.LikeArtistUseCase;
import music.domain.model.artist.ArtistFavorite;

@RestController
@RequestMapping("/api/member/artist")
@AllArgsConstructor
public class LikeArtistController {
    private final LikeArtistUseCase useCase;

    /**
     * アーティストをお気に入り登録する。
     */
    @PostMapping("/favorite")
    public ArtistFavorite likeArtist(@RequestBody @Valid final RegisterArtistFavoriteDto param) {
        return useCase.LikeArtist(param);
    }
}
