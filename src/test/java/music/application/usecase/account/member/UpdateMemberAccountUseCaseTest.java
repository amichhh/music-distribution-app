package music.application.usecase.account.member;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import music.application.dto.account.UpdateMemberAccountDto;
import music.application.usecase.security.GetAuthenticationUseCase;
import music.domain.model.account.Account;
import music.domain.model.account.AccountId;
import music.domain.model.account.AccountRepository;
import music.domain.model.account.EncodedPassword;
import music.domain.model.account.type.AuthorityType;

@ExtendWith(MockitoExtension.class)
public class UpdateMemberAccountUseCaseTest {

    @InjectMocks
    private UpdateMemberAccountUseCase updateMemberAccountUseCase;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private GetAuthenticationUseCase getAuthenticationUseCase;

    Account account1;

    @BeforeEach
    void setUp() {
        account1 = Account.create(
                AccountId.of("test_member_id_1"),
                "test_member_name_1",
                EncodedPassword.of("TestPass1"),
                AuthorityType.MEMBER);
    }

    @Test
    void updateMemberAccount() {
        String accountId = "test_member_id_1";
        String name = "test_member_name_change";
        String password = "TestPass0Change";
        UpdateMemberAccountDto param = new UpdateMemberAccountDto(accountId, name, password);

        ArgumentCaptor<Account> captor = ArgumentCaptor.forClass(Account.class);
        when(accountRepository.load(any())).thenReturn(account1);
        when(accountRepository.update(captor.capture())).thenReturn(null);
        when(getAuthenticationUseCase.accountId()).thenReturn(AccountId.of("test_member_id_1"));

        updateMemberAccountUseCase.updateMemberAccount(param);

        Account captured = captor.getValue();
        assertEquals(accountId, captured.getAccountId());
        assertEquals(name, captured.getName());
        assertEquals(AuthorityType.MEMBER, captured.getAuthority());
        assertTrue(captured.isValid());
    }
}
