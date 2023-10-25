package music.domain.model.account;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncodedPassword {
    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private static final int MAX_LENGTH = 32;
    private static final int MIN_LENGTH = 8;
    final String value;

    private EncodedPassword(final String rawPassword) {
        if (rawPassword.length() < MIN_LENGTH || MAX_LENGTH < rawPassword.length()) {
            throw new IllegalArgumentException(
                    String.format("パスワードが%s文字以上%s文字以下ではありません。", MIN_LENGTH, MAX_LENGTH));
        }
        this.value = encoder.encode(rawPassword);
    }

    /**
     * ハッシュ化されたパスワードを生成する。
     */
    public static EncodedPassword create(final String rawPassword) {
        return new EncodedPassword(rawPassword);
    }

    public String value() {
        return this.value;
    }
}