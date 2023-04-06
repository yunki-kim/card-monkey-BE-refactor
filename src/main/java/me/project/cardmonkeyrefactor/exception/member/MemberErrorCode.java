package me.project.cardmonkeyrefactor.exception.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.project.cardmonkeyrefactor.exception.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements ErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당 회원 정보를 찾을 수 없습니다."),
    DUPLICATE_INPUT_PASSWORD(HttpStatus.BAD_REQUEST, "현재 비밀번호와 새 비밀번호는 동일할 수 없습니다."),
    ALREADY_USE_ID(HttpStatus.CONFLICT, "이미 사용중인 아이디입니다. 다른 아이디를 입력해주세요.");

    private final HttpStatus httpStatus;
    private final String message;
}
