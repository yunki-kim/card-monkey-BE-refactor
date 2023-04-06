package me.project.cardmonkeyrefactor.exception.member;

import me.project.cardmonkeyrefactor.exception.CardMonkeyException;

public class MismatchCurrentPasswordException extends CardMonkeyException {

    public MismatchCurrentPasswordException() {
        super(MemberErrorCode.MISMATCH_CURRENT_PASSWORD);
    }
}
