package me.shinsunyoung.springbootdeveloper.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties("jwt")//프로퍼티의 값을 가져와서 자바 클래스에 사용
public class JwtProperties {

    private String issuer;
    private String secretKey;
}

