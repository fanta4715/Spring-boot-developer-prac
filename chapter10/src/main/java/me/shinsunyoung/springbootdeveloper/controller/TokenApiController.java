package me.shinsunyoung.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.dto.CreateAccessTokenRequest;
import me.shinsunyoung.springbootdeveloper.dto.CreateAccessTokenResponse;
import me.shinsunyoung.springbootdeveloper.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//RefreshToken은 보통 httpOnly Cookie를 통해서 전달
//그냥 JWT(AccessToken)는 JSON형식으로 헤더에 담고, 클라이언트(브라우저)가 저장하는 방식
@RequiredArgsConstructor
@RestController
public class TokenApiController {

    //TokenService만 주입한 이유
    //TokenService는 UserService, RefreshTokenService, TokenProvider를 주입받고 있음
    //따라서 TokenService만 주입받으면됨.
    //애초에 RefreshToken을 하든 뭘 하든, 하나의 컨트롤러에 매핑되야 함!!
    private final TokenService tokenService;

    // 리프레시 토큰을 기반으로 새로운 엑세스 토큰을 만들어줌
    @PostMapping("/api/token")
    public ResponseEntity<CreateAccessTokenResponse> createNewAccessToken(@RequestBody CreateAccessTokenRequest request) {
        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateAccessTokenResponse(newAccessToken));
    }
}
