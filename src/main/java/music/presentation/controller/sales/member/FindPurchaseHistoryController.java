package music.presentation.controller.sales.member;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import music.application.usecase.sales.member.FindPurchaseHistoryUseCase;
import music.domain.model.sales.SalesRecord;

@RestController
@RequestMapping("/api/member/sales")
@AllArgsConstructor
public class FindPurchaseHistoryController {
    private final FindPurchaseHistoryUseCase useCase;

    /**
     * 自身の購入履歴を検索する。
     */
    @GetMapping("/history")
    public List<SalesRecord> findPurchaseHistory() {
        return useCase.findPurchaseHistory();
    }
}
