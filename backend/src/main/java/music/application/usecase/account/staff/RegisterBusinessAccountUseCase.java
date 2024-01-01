package music.application.usecase.account.staff;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import music.application.dto.account.RegisterBusinessAccountDto;
import music.domain.model.account.Account;
import music.domain.model.account.AccountId;
import music.domain.model.account.AccountRepository;
import music.domain.model.account.Belong;
import music.domain.model.account.BelongRepository;
import music.domain.model.account.EncodedPassword;
import music.domain.model.account.type.AuthorityType;

@Service
@Transactional
@AllArgsConstructor
public class RegisterBusinessAccountUseCase {
    private final AccountRepository accountRep;
    private final BelongRepository belongRep;

    /**
     * ビジネスアカウントを登録する。
     * 
     * @param param ビジネスアカウント登録用DTO
     * @return 登録したビジネスアカウント
     */
    public Account registerBusinessAccount(final RegisterBusinessAccountDto param) {
        AccountId accountId = AccountId.of(param.getAccountId());
        if (accountRep.existsByAccountId(accountId)) {
            throw new RuntimeException("アカウントIDが既に登録されています。");
        }
        // 所属先の登録
        belongRep.store(createBelong(accountId, param.getCompanyId()));
        return accountRep.store(createBusinessAccount(param));
    }

    /**
     * ビジネスアカウントのインスタンスを生成する。
     */
    private Account createBusinessAccount(RegisterBusinessAccountDto param) {
        return Account.create(AccountId.of(param.getAccountId()), param.getName(),
                EncodedPassword.of(param.getPassword()), AuthorityType.BUSINESS);
    }

    /**
     * 所属先のインスタンスを生成する。
     */
    private Belong createBelong(AccountId accountId, String companyId) {
        return Belong.create(accountId, companyId);
    }

}
