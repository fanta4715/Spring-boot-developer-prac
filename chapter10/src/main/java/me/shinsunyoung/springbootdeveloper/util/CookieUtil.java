package me.shinsunyoung.springbootdeveloper.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.SerializationUtils;

import java.util.Base64;

//인증 플로우를 구현할 때 쿠키를 사용할 일이 생김
//유틸리티로 쿠키 관리 클래스를 미리 구현함
public class CookieUtil {

    //요청값(이름, 값, 만료기간)을 바탕으로 쿠키 추가
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);

        response.addCookie(cookie);
    }

    //쿠키의 이름을 입력받아 쿠키 삭제
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        //쿠키는 요청시에 단 하나만 들어오는 것이 아니다!
        //여러개 들어올 수 있다.
        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            return;
        }

        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                //쿠키의 핵심변수라고 할 수 있는 value
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);

                //쿠키 값 삭제 후  response에 보냄
                //왜?
                response.addCookie(cookie);
            }
        }
    }

    // 객체 --- 직렬화 ---> 쿠키
    public static String serialize(Object obj) {
        return Base64.getUrlEncoder()
                .encodeToString(SerializationUtils.serialize(obj));
    }

    // 쿠키 --- 역직렬화 ---> 객체
    public static <T> T deserialize(Cookie cookie, Class<T> cls) {
        return cls.cast(
                SerializationUtils.deserialize(
                        Base64.getUrlDecoder().decode(cookie.getValue())
                )
        );
    }
}

