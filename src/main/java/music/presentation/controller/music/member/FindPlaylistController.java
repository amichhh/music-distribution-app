package music.presentation.controller.music.member;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import music.application.usecase.music.member.FindPlaylistUseCase;
import music.domain.model.music.Playlist;

@RestController
@RequestMapping("/api/member/music")
@AllArgsConstructor
public class FindPlaylistController {
    private final FindPlaylistUseCase useCase;

    /**
     * 自身のプレイリストを検索する。
     */
    @GetMapping("/playlist")
    public List<Playlist> findPlaylist() {
        return useCase.findPlaylist();
    }
}
