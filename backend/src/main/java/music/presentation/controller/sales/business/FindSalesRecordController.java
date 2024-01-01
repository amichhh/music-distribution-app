package music.presentation.controller.sales.business;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import music.application.dto.sales.FindSalesRecordDto;
import music.application.usecase.sales.business.FindSalesRecordUseCase;
import music.domain.model.sales.SalesRecord;

@RestController
@RequestMapping("/api/business/sales")
@AllArgsConstructor
public class FindSalesRecordController {
    private final FindSalesRecordUseCase useCase;

    /**
     * 自身の所属する企業の日付ごとの売上記録を検索する。
     */
    @GetMapping("/day")
    public List<SalesRecord> findSalesRecordPerDay(final FindSalesRecordDto param) {
        return useCase.findSalesRecordPerDay(param);
    }
}
