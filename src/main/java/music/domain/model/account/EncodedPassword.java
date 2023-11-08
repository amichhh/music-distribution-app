package music.domain.model.account;

import java.util.regex.Pattern;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncodedPassword {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private static final int MAX_LENGTH = 32;
    private static final int MIN_LENGTH = 8;
    private static final String REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).*$";
    final String value;

    private EncodedPassword(final String rawPassword) {
        // 文字数チェック
        if (rawPassword.length() < MIN_LENGTH || MAX_LENGTH < rawPassword.length()) {
            throw new IllegalArgumentException(
                    String.format("パスワードが%s文字以上%s文字以下ではありません。", MIN_LENGTH, MAX_LENGTH));
        }
        // 正規表現チェック
        Pattern pattern = Pattern.compile(REGEX);
        if (!pattern.matcher(rawPassword).matches()) {
            throw new IllegalArgumentException("パスワードが英大文字小文字と数字をそれぞれ1文字ずつ含む文字列ではありません。");
        }
        this.value = encoder.encode(rawPassword);
    }

    /**
     * ハッシュ化されたパスワードを生成する。
     */
    public static EncodedPassword of(final String rawPassword) {
        return new EncodedPassword(rawPassword);
    }

    public String value() {
        return this.value;
    }
}
