package music.domain.model.music;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import music.domain.model.music.type.MusicStatusType;
import music.domain.shared.exception.InvalidStateTransitionException;

@Entity
@Getter
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String title;
    @NotNull
    private Long artistId;
    @NotBlank
    private String companyId;
    @NotNull
    @Min(0)
    private int price;
    private LocalDate releaseDay;
    @Enumerated(EnumType.STRING)
    private MusicStatusType status;
    @NotNull
    @Min(0)
    private int purchaseCount;

    private Music() {
    }

    private Music(final String title, final Long artistId, final String companyId, final int price,
            final LocalDate releaseDay, final MusicStatusType status, final int purchaseCount) {
        this.title = title;
        this.artistId = artistId;
        this.companyId = companyId;
        this.price = price;
        this.releaseDay = releaseDay;
        this.status = status;
        this.purchaseCount = purchaseCount;
    }

    /**
     * 楽曲を作成する。
     */
    public static Music create(final String title, final Long artistId, final String companyId, final int price,
            final LocalDate releaseDay) {
        MusicStatusType musicStatus = releaseDay.isAfter(LocalDate.now()) ? MusicStatusType.UNAVAILABLE
                : MusicStatusType.AVAILABLE;
        return new Music(title, artistId, companyId, price, releaseDay, musicStatus, 0);
    }

    /**
     * 楽曲の状態を利用可能にする。
     */
    public Music enable() {
        if (this.isAvailable()) {
            throw new InvalidStateTransitionException("楽曲は既に利用可能です。");
        }
        this.status = MusicStatusType.AVAILABLE;
        return this;
    }

    /**
     * 楽曲の購入回数を1増やす。
     */
    public Music plusPurchaseCount() {
        this.purchaseCount += 1;
        return this;
    }

    public Boolean isAvailable() {
        return this.status == MusicStatusType.AVAILABLE;
    }

    public Boolean isUnavailable() {
        return this.status == MusicStatusType.UNAVAILABLE;
    }

    public Boolean canRelease() {
        LocalDate today = LocalDate.now();
        return this.releaseDay.isEqual(today) || this.releaseDay.isAfter(today);
    }
}
