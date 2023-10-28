package music.domain.model.music;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import music.domain.model.music.type.MusicStatusType;

@Entity
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String title;
    @NotNull
    private Long artistId;
    @NotNull
    @Min(0)
    private int price;
    @NotNull
    private LocalDate releaseDay;
    @Enumerated
    private MusicStatusType musicStatus;
    @NotNull
    @Min(0)
    private int purchaseCount;

    private Music(final String title, final Long artistId, final int price, final LocalDate releaseDay,
            final MusicStatusType musicStatus, final int purchaseCount) {
        this.title = title;
        this.artistId = artistId;
        this.price = price;
        this.releaseDay = releaseDay;
        this.musicStatus = musicStatus;
        this.purchaseCount = purchaseCount;
    }

    /**
     * 楽曲を作成する。
     */
    public static Music create(final String title, final Long artistId, final int price, final LocalDate releaseDay) {
        MusicStatusType musicStatus = releaseDay.isAfter(LocalDate.now()) ? MusicStatusType.UNAVAILABLE
                : MusicStatusType.AVAILABLE;
        return new Music(title, artistId, price, releaseDay, musicStatus, 0);
    }

    /**
     * 楽曲の状態を利用可能にする。
     */
    public Music enable() {
        if (this.isAvailable()) {
            throw new RuntimeException("楽曲は既に利用可能です。");
        }
        this.musicStatus = MusicStatusType.AVAILABLE;
        return this;
    }

    public Boolean isAvailable() {
        return this.musicStatus == MusicStatusType.AVAILABLE;
    }

    public Boolean isUnavailable() {
        return this.musicStatus == MusicStatusType.UNAVAILABLE;
    }

    public Boolean canRelease() {
        LocalDate today = LocalDate.now();
        return this.releaseDay.isEqual(today) || this.releaseDay.isAfter(today);
    }

    public Long id() {
        return this.id;
    }

    public int price() {
        return this.price;
    }
}