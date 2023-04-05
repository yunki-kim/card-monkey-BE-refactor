package me.project.cardmonkeyrefactor.exception.member;

import me.project.cardmonkeyrefactor.exception.CardMonkeyException;

public class DuplicateInputPasswordException extends CardMonkeyException {

    public DuplicateInputPasswordException() {
        super(MemberErrorCode.DUPLICATE_INPUT_PASSWORD);
    }
}
