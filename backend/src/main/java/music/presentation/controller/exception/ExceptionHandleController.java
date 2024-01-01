package music.presentation.controller.exception;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;
import music.domain.shared.exception.InvalidStateTransitionException;
import music.infrastructure.shared.exception.DataNotFoundException;

@RestControllerAdvice
@Slf4j
public class ExceptionHandleController extends ResponseEntityExceptionHandler {

    /**
     * リクエストのパラメータが正しくない場合の例外処理を行う。
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        HashMap<String, String> errors = new HashMap<>();
        fieldErrors.forEach(v -> {
            errors.put(v.getField(), v.getDefaultMessage());
        });
        ErrorResponse error = new ErrorResponse(status.value(), "入力された値が正しくありません。",
                errors);
        return this.handleExceptionInternal(ex, error, headers, status, request);
    }

    /**
     * ユースケースで発生した例外処理を行う。
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        log.warn(ex.getMessage());
        return new ResponseEntity<Object>(error, new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 存在しないデータにアクセスした場合の例外処理を行う。
     */
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Object> handleDataNotFoundException(DataNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        log.warn(ex.getMessage());
        return new ResponseEntity<Object>(error, new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 許可されていないアクセスが発生した場合の例外処理を行う。
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(),
                ex.getMessage());
        log.warn(ex.getMessage());
        return new ResponseEntity<Object>(error, new HttpHeaders(),
                HttpStatus.UNAUTHORIZED);
    }

    /**
     * 無効な状態遷移が発生した場合の例外処理を行う。
     */
    @ExceptionHandler(InvalidStateTransitionException.class)
    public ResponseEntity<Object> handleInvalidStateTransitionException(InvalidStateTransitionException ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        log.warn(ex.getMessage());
        return new ResponseEntity<Object>(error, new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "予期せぬ例外が発生しました。");
        log.error(ex.getMessage());
        ex.printStackTrace();
        return new ResponseEntity<Object>(error, new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
