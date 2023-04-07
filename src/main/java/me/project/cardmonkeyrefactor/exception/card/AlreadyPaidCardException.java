package me.project.cardmonkeyrefactor.exception.card;

import me.project.cardmonkeyrefactor.exception.CardMonkeyException;

public class AlreadyPaidCardException extends CardMonkeyException {

    public AlreadyPaidCardException() {
        super(CardErrorCode.ALREADY_PAID_CARD);
    }
}
