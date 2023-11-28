package music.application.usecase.account.staff;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import music.domain.model.account.AccountRepository;

@ExtendWith(MockitoExtension.class)
public class FindAccountUseCaseTest {

    @InjectMocks
    private FindAccountUseCase findAccountUseCase;

    @Mock
    private AccountRepository accountRepository;

}
