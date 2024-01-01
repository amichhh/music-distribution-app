package music.presentation.controller.artist.business;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import music.application.dto.artist.UpdateArtistDto;
import music.application.usecase.artist.business.UpdateArtistUseCase;
import music.domain.model.artist.Artist;

@RestController
@RequestMapping("/api/business/artist")
@AllArgsConstructor
public class EditArtistController {
    private final UpdateArtistUseCase useCase;

    /**
     * アーティストを編集する。
     */
    @PostMapping("/edit")
    public Artist editArtist(@RequestBody @Valid final UpdateArtistDto param) {
        return useCase.editArtist(param);
    }
}
