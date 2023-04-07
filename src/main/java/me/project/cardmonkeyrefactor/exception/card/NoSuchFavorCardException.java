package me.project.cardmonkeyrefactor.exception.card;

import me.project.cardmonkeyrefactor.exception.CardMonkeyException;

public class NoSuchFavorCardException extends CardMonkeyException {

    public NoSuchFavorCardException() {
        super(CardErrorCode.FAVOR_CARD_NOT_FOUND);
    }
}
