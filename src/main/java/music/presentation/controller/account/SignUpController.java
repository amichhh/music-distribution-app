package music.presentation.controller.account;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import music.application.dto.account.RegisterMemberAccountDto;
import music.application.usecase.account.RegisterMemberAccountUseCase;
import music.domain.model.account.Account;

@RestController
@RequestMapping("/api/account")
@AllArgsConstructor
public class SignUpController {
    private final RegisterMemberAccountUseCase useCase;

    /**
     * サインアップする。
     */
    @PostMapping("/signup")
    public Account signUp(@RequestBody @Valid final RegisterMemberAccountDto param) {
        return useCase.registerMemberAccount(param);
    }
}
