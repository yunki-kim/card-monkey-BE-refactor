package me.project.cardmonkeyrefactor.exception.member;

import me.project.cardmonkeyrefactor.exception.CardMonkeyException;

public class NoSuchMemberException extends CardMonkeyException {

    public NoSuchMemberException() {
        super(MemberErrorCode.MEMBER_NOT_FOUND);
    }
}
