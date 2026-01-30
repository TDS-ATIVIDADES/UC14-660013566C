package app.cinema.demo.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.expiration.minutes:60}")
    private long expirationMinutes;

    private SecretKey key;
    private long defaultExpirationMillis;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.defaultExpirationMillis = expirationMinutes * 60 * 1000;
    }

    // Default constructor for Spring
    public JwtUtil() {}

    // Constructor for testing
    JwtUtil(SecretKey key, long defaultExpirationMillis) {
        this.key = key;
        this.defaultExpirationMillis = defaultExpirationMillis;
        this.secretKey = ""; // not used in tests
        this.expirationMinutes = 0; // not used
    }

    public String generateToken(String username) {
        return generateToken(username, defaultExpirationMillis);
    }

    public String generateToken(String username, long expirationMillis) {
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
            .signWith(key)
            .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean isTokenExpired(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getExpiration().before(new Date());
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            return true;
        }
    }

    public boolean validateToken(String token, String username) {
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }
}