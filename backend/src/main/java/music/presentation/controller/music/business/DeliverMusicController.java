package music.presentation.controller.music.business;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import music.application.dto.music.RegisterMusicDto;
import music.application.usecase.music.business.RegisterMusicUseCase;
import music.domain.model.music.Music;

@RestController
@RequestMapping("/api/business/music")
@AllArgsConstructor
public class DeliverMusicController {
    private final RegisterMusicUseCase useCase;

    /**
     * 楽曲を納品する。
     */
    @PostMapping("")
    public Music deliverMusic(@RequestBody @Valid final RegisterMusicDto param) {
        return useCase.registerMusic(param);
    }
}
