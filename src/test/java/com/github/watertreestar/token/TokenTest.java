package com.github.watertreestar.token;

import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

public class TokenTest {
    private static String key = "234feaftfaefafe3";

    private static byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);

    @Test
    public void testGenerateByString() {
        try {
            String tokenSeq = Token.generateToken(keyBytes, "111", "watertreestar", "ranger");
            Assert.assertNotEquals(null, tokenSeq);

            boolean isValid = Token.isTokenValid(keyBytes, "111", tokenSeq);
            Assert.assertTrue(isValid);
        } catch (Exception e) {
            Assert.fail();
        }
    }
}
