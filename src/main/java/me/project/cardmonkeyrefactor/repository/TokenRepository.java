package me.project.cardmonkeyrefactor.repository;

import me.project.cardmonkeyrefactor.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, String> {

    boolean existsByToken(String token);
}
