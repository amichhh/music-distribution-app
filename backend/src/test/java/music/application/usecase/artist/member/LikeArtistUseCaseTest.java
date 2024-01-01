package music.application.usecase.artist.member;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import music.application.dto.artist.RegisterArtistFavoriteDto;
import music.application.usecase.security.GetAuthenticationUseCase;
import music.domain.model.account.AccountId;
import music.domain.model.artist.ArtistFavorite;
import music.domain.model.artist.ArtistFavoriteRepository;

@ExtendWith(MockitoExtension.class)
public class LikeArtistUseCaseTest {

    @InjectMocks
    private LikeArtistUseCase likeArtistUseCase;

    @Mock
    private ArtistFavoriteRepository artistFavoriteRepository;

    @Mock
    private GetAuthenticationUseCase getAuthenticationUseCase;

    @Test
    void likeArtist() {
        Long artistId = 1L;
        String accountId = "test_member_id";
        RegisterArtistFavoriteDto param = new RegisterArtistFavoriteDto(artistId);

        ArgumentCaptor<ArtistFavorite> captor = ArgumentCaptor.forClass(ArtistFavorite.class);
        when(artistFavoriteRepository.isLiked(any(), any())).thenReturn(false);
        when(artistFavoriteRepository.store(captor.capture())).thenReturn(null);
        when(getAuthenticationUseCase.accountId()).thenReturn(AccountId.of(accountId));

        likeArtistUseCase.likeArtist(param);

        ArtistFavorite captured = captor.getValue();
        assertEquals(artistId, captured.getArtistId());
        assertEquals(accountId, captured.getAccountId());
    }

    @Test
    void likeArtist_ex() {
        Long artistId = 1L;
        RegisterArtistFavoriteDto param = new RegisterArtistFavoriteDto(artistId);

        when(artistFavoriteRepository.isLiked(any(), any())).thenReturn(true);

        assertThrows(RuntimeException.class, () -> likeArtistUseCase.likeArtist(param));
    }

}
