package music.presentation.controller.artist.member;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import music.application.dto.artist.RemoveArtistFavoriteDto;
import music.application.usecase.artist.member.RemoveArtistFavoriteUseCase;

@RestController
@RequestMapping("/api/member/artist")
@AllArgsConstructor
public class RemoveArtistFavoriteController {
    private final RemoveArtistFavoriteUseCase useCase;

    /**
     * アーティストのお気に入りを解除する。
     */
    @PostMapping("/favorite/remove")
    public void removeArtistFavorite(@RequestBody @Valid final RemoveArtistFavoriteDto param) {
        useCase.deleteArtistFavorite(param);
    }
}
