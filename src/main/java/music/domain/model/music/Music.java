package music.domain.model.music;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import music.domain.model.music.type.MusicStatusType;
import music.domain.shared.IdGenerator;

@Entity
public class Music {
    @Id
    private String musicId;
    @NotBlank
    private String title;
    @NotBlank
    private String artistId;
    @NotBlank
    @Min(0)
    private int price;
    @NotBlank
    private LocalDate releaseDay;
    @NotBlank
    private LocalDateTime publishDate;
    @Enumerated
    private MusicStatusType musicStatus;
    @NotBlank
    @Min(0)
    private int purchaseCount;

    private Music(final String musicId, final String title, final String artistId, final int price,
            final LocalDate releaseDay, final LocalDateTime publishDate, final MusicStatusType musicStatus,
            final int purchaseCount) {
        this.musicId = musicId;
        this.title = title;
        this.artistId = artistId;
        this.price = price;
        this.releaseDay = releaseDay;
        this.publishDate = publishDate;
        this.musicStatus = musicStatus;
        this.purchaseCount = purchaseCount;
    }

    /**
     * 楽曲を作成する。
     */
    public static Music create(final String title, final String artistId, final int price,
            final LocalDate releaseDay, final LocalDateTime publishDate, final int purchaseCount) {
        MusicStatusType musicStatus = publishDate.isAfter(LocalDateTime.now()) ? MusicStatusType.UNAVAILABLE
                : MusicStatusType.AVAILABLE;
        return new Music(IdGenerator.genarateMusicId(), title, artistId, price, releaseDay, publishDate, musicStatus,
                0);
    }

    public Boolean isAvailable() {
        return this.musicStatus == MusicStatusType.AVAILABLE;
    }

    public Boolean isUnavailable() {
        return this.musicStatus == MusicStatusType.UNAVAILABLE;
    }
}