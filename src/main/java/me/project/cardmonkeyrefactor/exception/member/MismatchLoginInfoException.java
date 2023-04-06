package me.project.cardmonkeyrefactor.exception.member;

import me.project.cardmonkeyrefactor.exception.CardMonkeyException;

public class MismatchLoginInfoException extends CardMonkeyException {

    public MismatchLoginInfoException() {
        super(MemberErrorCode.MISMATCH_LOGIN_INFO);
    }
}
