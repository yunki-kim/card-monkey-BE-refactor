package me.project.cardmonkeyrefactor.exception.card;

import me.project.cardmonkeyrefactor.exception.CardMonkeyException;

public class NoSuchCardException extends CardMonkeyException {

    public NoSuchCardException() {
        super(CardErrorCode.CARD_NOT_FOUND);
    }
}
