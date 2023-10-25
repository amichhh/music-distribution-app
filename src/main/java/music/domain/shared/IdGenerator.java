package music.domain.shared;

import java.util.UUID;

public class IdGenerator {
    private final static String MUSIC_PREFIX = "MUS";
    private final static String ARTIST_PREFIX = "ART";

    private static String generate() {
        return UUID.randomUUID().toString();
    }

    public static String genarateMusicId() {
        return MUSIC_PREFIX + generate();
    }

    public static String genarateArtistId() {
        return ARTIST_PREFIX + generate();
    }

}