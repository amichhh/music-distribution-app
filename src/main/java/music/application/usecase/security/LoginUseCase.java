package music.application.usecase.security;

import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import music.domain.model.account.Account;
import music.domain.model.account.AccountRepository;

@Service
@AllArgsConstructor
public class LoginUseCase implements UserDetailsService {
    private final AccountRepository rep;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Account account = rep.loadByLoginId(loginId);
        if (account.isInvalid()) {
            throw new RuntimeException("アカウントが無効です。");
        }
        return new User(account.getAccountId(), account.getPassword(),
                Arrays.asList(new SimpleGrantedAuthority((account.getAuthority().toString()))));
    }
}
