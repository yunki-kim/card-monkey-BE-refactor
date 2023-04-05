package me.project.cardmonkeyrefactor.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {

    INVALID_INPUT(HttpStatus.BAD_REQUEST, "유효하지 않은 입력값 입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
