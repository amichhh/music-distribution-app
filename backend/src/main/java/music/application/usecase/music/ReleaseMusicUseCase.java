package music.application.usecase.music;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import music.domain.model.music.MusicRepository;

@EnableScheduling
@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class ReleaseMusicUseCase {
    private final MusicRepository rep;

    /**
     * リリース日を迎えた楽曲を利用可能にする。
     */
    @Scheduled(cron = "${app.cron.music.release}")
    public void releaseMusic() {
        rep.search().stream().filter(v -> v.isUnavailable() && v.canRelease()).forEach(v -> {
            rep.update(v.enable());
            log.info("Music became available. [ {} ]", v.getId());
        });
    }
}
