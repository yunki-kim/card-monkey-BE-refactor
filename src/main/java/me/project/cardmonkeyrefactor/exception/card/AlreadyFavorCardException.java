package me.project.cardmonkeyrefactor.exception.card;

import me.project.cardmonkeyrefactor.exception.CardMonkeyException;

public class AlreadyFavorCardException extends CardMonkeyException {

    public AlreadyFavorCardException() {
        super(CardErrorCode.ALREADY_FAVOR_CARD);
    }
}
