package music.presentation.controller.account.staff;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import music.application.dto.account.RegisterBusinessAccountDto;
import music.application.usecase.account.staff.RegisterBusinessAccountUseCase;
import music.domain.model.account.Account;

@RestController
@RequestMapping("/api/staff/account")
@AllArgsConstructor
public class RegisterBusinessAccountController {
    private final RegisterBusinessAccountUseCase useCase;

    /**
     * ビジネスアカウントを登録する。
     */
    @PostMapping("/business")
    public Account registerBusinessAccount(@RequestBody @Valid final RegisterBusinessAccountDto param) {
        return useCase.registerBusinessAccount(param);
    }
}
