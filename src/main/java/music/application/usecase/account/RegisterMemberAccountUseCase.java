package music.application.usecase.account;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import music.application.dto.account.RegisterMemberAccountDto;
import music.domain.model.account.Account;
import music.domain.model.account.AccountId;
import music.domain.model.account.AccountRepository;
import music.domain.model.account.EncodedPassword;
import music.domain.model.account.type.AuthorityType;

@Service
@Transactional
@AllArgsConstructor
public class RegisterMemberAccountUseCase {
    private final AccountRepository rep;

    /**
     * メンバーアカウントを登録する。
     * 
     * @param param メンバーアカウント登録用DTO
     * @return 登録したメンバーアカウント
     */
    public Account registerMemberAccount(final RegisterMemberAccountDto param) {
        AccountId accountId = AccountId.create(param.getAccountId());
        if (rep.existsByAccountId(accountId)) {
            throw new RuntimeException("アカウントIDが既に登録されています。");
        }
        return rep.store(createMemberAccount(param));
    }

    /**
     * メンバーアカウントのインスタンスを生成する。
     */
    private Account createMemberAccount(final RegisterMemberAccountDto param) {
        return Account.create(AccountId.create(param.getAccountId()), param.getName(),
                EncodedPassword.create(param.getPassword()), AuthorityType.MEMBER);
    }

}
