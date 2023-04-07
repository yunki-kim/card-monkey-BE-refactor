package me.project.cardmonkeyrefactor.exception.card;

import me.project.cardmonkeyrefactor.exception.CardMonkeyException;

public class NoSuchPaidCardException extends CardMonkeyException {

    public NoSuchPaidCardException() {
        super(CardErrorCode.PAID_CARD_NOT_FOUND);
    }
}
