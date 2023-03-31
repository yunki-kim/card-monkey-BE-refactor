package me.project.cardmonkeyrefactor.repository;

import lombok.RequiredArgsConstructor;
import me.project.cardmonkeyrefactor.entity.Card;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
public class CardRepositoryCustomImpl implements CardRepositoryCustom {

    private final EntityManager em;

    @Override
    public List<Card> findAllByBenefit(String benefitName, int offset, int limit) {
        String sql = "select c from Card c where c.benefit.";
        sql += benefitName;
        String postSql = " = :yes";
        sql += postSql;

        return em.createQuery(sql, Card.class)
                .setParameter("yes", "yes")
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    public Card findRandomCardByBenefit(String benefitName, int offset) {
        String sql = "select c from Card c where c.benefit.";
        sql += benefitName;
        String postSql = " = :yes";
        sql += postSql;

        return em.createQuery(sql, Card.class)
                .setParameter("yes", "yes")
                .setFirstResult(offset)
                .setMaxResults(1)
                .getSingleResult();
    }

    @Override
    public long countByBenefit(String benefitName) {
        String sql = "select count(c) from Card c where c.benefit.";
        sql += benefitName;
        String postSql = " = :yes";
        sql += postSql;

        return em.createQuery(sql, Long.class)
                .setParameter("yes", "yes")
                .getSingleResult();
    }
}
