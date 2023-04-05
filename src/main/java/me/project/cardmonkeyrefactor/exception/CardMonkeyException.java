package me.project.cardmonkeyrefactor.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CardMonkeyException extends RuntimeException {

    private final ErrorCode errorCode;
}
