package music.application.usecase.music.member;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import music.application.dto.music.RegisterPlaylistItemDto;
import music.domain.model.music.PlaylistItem;
import music.domain.model.music.PlaylistItemRepository;

@Service
@Transactional
@AllArgsConstructor
public class RegisterPlaylistItemUseCase {
    private final PlaylistItemRepository rep;

    /**
     * プレイリストアイテムを登録する。
     * 
     * @param param プレイリストアイテム登録用DTO
     * @return 登録したプレイリストアイテム
     */
    public PlaylistItem registerPlaylistItem(final RegisterPlaylistItemDto param) {
        // TODO: 同じプレイリスト、同じ楽曲
        return rep.store(createPlaylistItem(param));
    };

    /**
     * プレイリストアイテムのインスタンスを生成する。
     */
    private PlaylistItem createPlaylistItem(final RegisterPlaylistItemDto param) {
        return PlaylistItem.create(param.getPlaylistId(), param.getMusicId(), rep.itemCount(param.getPlaylistId()) + 1);
    }
}
