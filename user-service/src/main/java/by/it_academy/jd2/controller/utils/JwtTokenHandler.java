package by.it_academy.jd2.controller.utils;

import by.it_academy.jd2.configuration.properties.JwtProperties;
import by.it_academy.jd2.service.dto.UserSecure;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenHandler {

    private final JwtProperties jwtProperties;

    public String generateToken(UserSecure user) {
        return generateToken(user.getMail(), user);               // Только User оставить
    }

    public String generateToken(String mail, UserSecure user) {
        return Jwts.builder()
                .setSubject(mail)
                .claim("id", user.getId())          // посмотреть что есть
                .claim("fio", user.getFio())
                .claim("role", user.getRole())
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(jwtProperties.getExpiration())))
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())
                .compact();
    }

    public String getUsername(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    public Date getExpirationDate(String token) {
        Claims claims = getClaims(token);
        return claims.getExpiration();
    }

    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(jwtProperties.getSecret()).parseClaimsJws(token);
            boolean tokenExpired = isTokenExpired(token); //тестирую//тестирую//тестирую//тестирую//тестирую//тестирую
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature - {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token - {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token - {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token - {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty - {}", ex.getMessage());
        }
        return false;
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecret())
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token)  {
        return getExpirationDate(token).before(new Date());
    }
}
