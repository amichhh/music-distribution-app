package music.presentation.controller.artist;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import music.application.dto.artist.FindArtistDto;
import music.application.usecase.artist.FindArtistUseCase;
import music.domain.model.artist.Artist;

@RestController
@RequestMapping("/api/artist")
@AllArgsConstructor
public class FindArtistController {
    private final FindArtistUseCase useCase;

    /**
     * アーティスト名でアーティストを検索する。
     */
    @GetMapping("")
    public List<Artist> findArtistByName(@Valid final FindArtistDto param) {
        return useCase.findArtistByName(param);
    }
}
