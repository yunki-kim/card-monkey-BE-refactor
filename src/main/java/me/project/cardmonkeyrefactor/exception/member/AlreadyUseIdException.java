package me.project.cardmonkeyrefactor.exception.member;

import me.project.cardmonkeyrefactor.exception.CardMonkeyException;

public class AlreadyUseIdException extends CardMonkeyException {

    public AlreadyUseIdException() {
        super(MemberErrorCode.ALREADY_USE_ID);
    }
}
