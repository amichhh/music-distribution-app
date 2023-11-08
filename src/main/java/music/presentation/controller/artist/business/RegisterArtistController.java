package music.presentation.controller.artist.business;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import music.application.dto.artist.RegisterArtistDto;
import music.application.usecase.artist.business.RegisterArtistUseCase;
import music.domain.model.artist.Artist;

@RestController
@RequestMapping("/api/business/artist")
@AllArgsConstructor
public class RegisterArtistController {
    private final RegisterArtistUseCase useCase;

    /**
     * アーティストを登録する。
     */
    @PostMapping("")
    public Artist registerArtist(@RequestBody @Valid final RegisterArtistDto param) {
        return useCase.registerArtist(param);
    }
}
