package music.application.usecase.account.member;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import music.application.dto.account.UpdateMemberAccountDto;
import music.application.usecase.security.GetAuthenticationUseCase;
import music.domain.model.account.Account;
import music.domain.model.account.AccountId;
import music.domain.model.account.AccountRepository;
import music.domain.model.account.EncodedPassword;

@Service
@Transactional
@AllArgsConstructor
public class UpdateMemberAccountUseCase {
    private final AccountRepository rep;
    private final GetAuthenticationUseCase authentication;

    /**
     * メンバーアカウントを更新する。
     * 
     * @param param メンバーアカウント更新用DTO
     * @return 更新したメンバーアカウント
     */
    public Account updateMemberAccount(final UpdateMemberAccountDto param) {
        AccountId loginUserId = authentication.accountId();
        Account current = rep.load(loginUserId);
        return rep.update(changeMemberAccount(current, param));
    }

    /**
     * メンバーアカウントのインスタンスを生成する。
     */
    private Account changeMemberAccount(final Account current, final UpdateMemberAccountDto param) {
        return current.change(param.getName(), EncodedPassword.of(param.getPassword()));
    }
}
