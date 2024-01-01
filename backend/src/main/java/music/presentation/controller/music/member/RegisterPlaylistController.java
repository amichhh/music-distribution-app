package music.presentation.controller.music.member;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import music.application.dto.music.RegisterPlaylistDto;
import music.application.usecase.music.member.RegisterPlaylistUseCase;
import music.domain.model.music.Playlist;

@RestController
@RequestMapping("/api/member/music")
@AllArgsConstructor
public class RegisterPlaylistController {
    private final RegisterPlaylistUseCase useCase;

    /**
     * プレイリストを登録する。
     */
    @PostMapping("/playlist")
    public Playlist registerPlaylist(@RequestBody @Valid final RegisterPlaylistDto param) {
        return useCase.registerPlaylist(param);
    }
}
