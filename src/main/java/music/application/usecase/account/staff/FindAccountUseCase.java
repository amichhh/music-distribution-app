package music.application.usecase.account.staff;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import music.domain.model.account.Account;
import music.domain.model.account.AccountRepository;

@Service
@Transactional
@AllArgsConstructor
public class FindAccountUseCase {
    private final AccountRepository rep;

    /**
     * メンバーアカウントを検索する。
     * 
     * @return メンバーアカウントリスト
     */
    public List<Account> findMemberAccount() {
        return rep.searchMember();
    }

    /**
     * ビジネスアカウントを検索する。
     * 
     * @return ビジネスアカウントリスト
     */
    public List<Account> findBusinessAccount() {
        return rep.searchBusiness();
    }

    /**
     * スタッフアカウントを検索する。
     * 
     * @return スタッフアカウントリスト
     */
    public List<Account> findStaffAccount() {
        return rep.searchStaff();
    }
}
