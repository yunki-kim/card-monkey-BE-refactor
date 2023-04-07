package me.project.cardmonkeyrefactor.exception.card;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.project.cardmonkeyrefactor.exception.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CardErrorCode implements ErrorCode {

    CARD_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당 카드 정보를 찾을 수 없습니다."),
    ALREADY_PAID_CARD(HttpStatus.CONFLICT, "이미 신청하신 카드입니다."),
    PAID_CARD_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당 카드의 신청내역이 존재하지 않습니다."),
    ALREADY_FAVOR_CARD(HttpStatus.CONFLICT, "이미 찜한 카드입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}

