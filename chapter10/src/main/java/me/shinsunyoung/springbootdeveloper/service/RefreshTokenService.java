package me.shinsunyoung.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.domain.RefreshToken;
import me.shinsunyoung.springbootdeveloper.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {

    //RefreshToken은 AccessToken과는 다르게 DB에 저장을 해야 한다.
    private final RefreshTokenRepository refreshTokenRepository;

    //RefreshToken을 만드는 건 TokenProvider에서 하면 됨
    //RefreshToken과 같은 String이 있는지 확인하는 함수
    //있으면 real RefreshToken을 반환해줌(String말고, entity)
    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected token"));
    }
}

