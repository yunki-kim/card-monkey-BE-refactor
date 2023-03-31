package me.project.cardmonkeyrefactor.repository;

import me.project.cardmonkeyrefactor.entity.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long>, CardRepositoryCustom {

    Optional<Card> findById(Long cardId);

    Page<Card> findAllByNameContains(String cardName, Pageable pageable);

    Page<Card> findAllByCompanyContains(String cardCompany, Pageable pageable);

    Page<Card> findAll(Pageable pageable);
}
