package music.presentation.controller.sales.member;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import music.application.dto.sales.PurchaseMusicDto;
import music.application.usecase.sales.member.PurchaseMusicUseCase;
import music.domain.model.sales.SalesRecord;

@RestController
@RequestMapping("/api/member/sales")
@AllArgsConstructor
public class PurchaseMusicController {
    private final PurchaseMusicUseCase useCase;

    /**
     * 楽曲を購入する。
     */
    @PostMapping("/purchase")
    public SalesRecord purchaseMusic(@RequestBody @Valid final PurchaseMusicDto param) {
        return useCase.purchaseMusic(param);
    }
}
