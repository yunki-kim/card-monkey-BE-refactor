package me.project.cardmonkeyrefactor.service;

import lombok.RequiredArgsConstructor;
import me.project.cardmonkeyrefactor.repository.TokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TokenService {

    private final TokenRepository tokenRepository;

    public boolean isTokenExists(String token) {
        return tokenRepository.existsByToken(token);
    }
}
