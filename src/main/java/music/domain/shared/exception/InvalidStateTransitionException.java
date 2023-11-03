package music.domain.shared.exception;

public class InvalidStateTransitionException extends RuntimeException {

    public InvalidStateTransitionException(final String message) {
        super(message);
    }

}
