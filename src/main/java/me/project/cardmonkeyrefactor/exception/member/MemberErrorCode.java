package me.project.cardmonkeyrefactor.exception.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.project.cardmonkeyrefactor.exception.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements ErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당 회원 정보를 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
