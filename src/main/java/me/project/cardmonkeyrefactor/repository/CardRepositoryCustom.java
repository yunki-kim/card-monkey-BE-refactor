package me.project.cardmonkeyrefactor.repository;

import me.project.cardmonkeyrefactor.entity.Card;

import java.util.List;

public interface CardRepositoryCustom {

    List<Card> findAllByBenefit(String benefitName, int offset, int limit);

    Card findRandomCardByBenefit(String benefitName, int offset);

    long countByBenefit(String benefitName);
}
