package app.cinema.demo.util;

import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private JwtUtil jwtUtil;
    private SecretKey testKey;

    @BeforeEach
    void setUp() {
        testKey = Keys.hmacShaKeyFor("test-secret-key-for-jwt-unit-test-1234567890".getBytes());
        jwtUtil = new JwtUtil(testKey, 3600000L); // 1 hour
    }

    @Test
    void testGenerateToken() {
        String token = jwtUtil.generateToken("user", 3600000); // 1 hour
        assertNotNull(token);
        assertTrue(token.contains("."));
    }

    @Test
    void testExtractUsername() {
        String token = jwtUtil.generateToken("user", 3600000);
        String username = jwtUtil.extractUsername(token);
        assertEquals("user", username);
    }

    @Test
    void testIsTokenExpired() {
        String token = jwtUtil.generateToken("user", -1000); // Expired
        assertTrue(jwtUtil.isTokenExpired(token));
    }

    @Test
    void testValidateToken() {
        String token = jwtUtil.generateToken("user", 3600000);
        assertTrue(jwtUtil.validateToken(token, "user"));
        assertFalse(jwtUtil.validateToken(token, "other"));
    }
}