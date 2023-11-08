package music.presentation.controller.account.staff;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import music.application.usecase.account.staff.FindAccountUseCase;
import music.domain.model.account.Account;

@RestController
@RequestMapping("/api/staff")
@AllArgsConstructor
public class FindAccountController {
    private final FindAccountUseCase useCase;

    /**
     * メンバーアカウントを検索する。
     */
    @GetMapping("/account/member")
    public List<Account> findMemberAccount() {
        return useCase.findMemberAccount();
    }

    /**
     * ビジネスアカウントを検索する。
     */
    @GetMapping("/account/business")
    public List<Account> findBusinessAccount() {
        return useCase.findBusinessAccount();
    }

    /**
     * スタッフアカウントを検索する。
     */
    @GetMapping("/account/staff")
    public List<Account> findStaffAccount() {
        return useCase.findStaffAccount();
    }
}
