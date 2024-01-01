package music.application.usecase.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import music.domain.model.account.Account;
import music.domain.model.account.AccountId;
import music.domain.model.account.AccountRepository;

@Service
@Transactional
@AllArgsConstructor
public class GetAuthenticationUseCase {
    private final AccountRepository rep;

    public Authentication authentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * ログイン中のユーザーのアカウントIDを返す。
     */
    public AccountId accountId() {
        String loginUserId = SecurityContextHolder.getContext().getAuthentication().getName();
        return AccountId.of(loginUserId);
    }

    /**
     * ログイン中のユーザーを返す。
     */
    public Account account() {
        AccountId loginUserId = AccountId.of(SecurityContextHolder.getContext().getAuthentication().getName());
        return rep.load(loginUserId);
    }
}
