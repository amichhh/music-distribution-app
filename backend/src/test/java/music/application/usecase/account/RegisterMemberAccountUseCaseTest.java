package music.application.usecase.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import music.application.dto.account.RegisterMemberAccountDto;
import music.domain.model.account.Account;
import music.domain.model.account.AccountRepository;
import music.domain.model.account.type.AuthorityType;

@ExtendWith(MockitoExtension.class)
public class RegisterMemberAccountUseCaseTest {

    @InjectMocks
    private RegisterMemberAccountUseCase registerMemberAccountUseCase;

    @Mock
    private AccountRepository accountRepository;

    @Test
    void registerMemberAccount() {
        String accountId = "test_member_id";
        String name = "test_member_name";
        String password = "TestPass0";
        RegisterMemberAccountDto param = new RegisterMemberAccountDto(accountId, name, password);

        ArgumentCaptor<Account> captor = ArgumentCaptor.forClass(Account.class);
        when(accountRepository.existsByAccountId(any())).thenReturn(false);
        when(accountRepository.store(captor.capture())).thenReturn(null);

        registerMemberAccountUseCase.registerMemberAccount(param);

        Account captured = captor.getValue();
        assertEquals(accountId, captured.getAccountId());
        assertEquals(name, captured.getName());
        assertEquals(AuthorityType.MEMBER, captured.getAuthority());
        assertTrue(captured.isValid());
    }

    @Test
    void registerMemberAccount_ex() {
        String id = "test_member_id";
        String name = "test_member_name";
        String password = "TestPass0";
        RegisterMemberAccountDto param = new RegisterMemberAccountDto(id, name, password);

        when(accountRepository.existsByAccountId(any())).thenReturn(true);

        assertThrows(RuntimeException.class, () -> registerMemberAccountUseCase.registerMemberAccount(param));
    }
}
