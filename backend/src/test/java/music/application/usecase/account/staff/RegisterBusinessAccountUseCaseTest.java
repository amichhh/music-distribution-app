package music.application.usecase.account.staff;

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

import music.application.dto.account.RegisterBusinessAccountDto;
import music.domain.model.account.Account;
import music.domain.model.account.AccountRepository;
import music.domain.model.account.BelongRepository;
import music.domain.model.account.type.AuthorityType;

@ExtendWith(MockitoExtension.class)
public class RegisterBusinessAccountUseCaseTest {

    @InjectMocks
    private RegisterBusinessAccountUseCase registerBusinessAccountUseCase;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private BelongRepository belongRepository;

    @Test
    void registerMemberAccount() {
        String accountId = "test_member_id";
        String name = "test_member_name";
        String companyId = "test_company_id";
        String password = "TestPass0";
        RegisterBusinessAccountDto param = new RegisterBusinessAccountDto(accountId, name, companyId, password);

        ArgumentCaptor<Account> captor = ArgumentCaptor.forClass(Account.class);
        when(accountRepository.existsByAccountId(any())).thenReturn(false);
        when(belongRepository.store(any())).thenReturn(null);
        when(accountRepository.store(captor.capture())).thenReturn(null);

        registerBusinessAccountUseCase.registerBusinessAccount(param);

        Account captured = captor.getValue();
        assertEquals(accountId, captured.getAccountId());
        assertEquals(name, captured.getName());
        assertEquals(AuthorityType.BUSINESS, captured.getAuthority());
        assertTrue(captured.isValid());
    }

    @Test
    void registerMemberAccount_ex() {
        String accountId = "test_member_id";
        String name = "test_member_name";
        String companyId = "test_company_id";
        String password = "TestPass0";
        RegisterBusinessAccountDto param = new RegisterBusinessAccountDto(accountId, name, companyId, password);

        when(accountRepository.existsByAccountId(any())).thenReturn(true);

        assertThrows(RuntimeException.class, () -> registerBusinessAccountUseCase.registerBusinessAccount(param));
    }
}
