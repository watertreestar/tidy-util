package com.github.watertreestar;

import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

public class CryptoUtilTest {
    @Test
    public void testEncrypt() {
        String key = "r343tere2es!ta@r";
        String value = "fafafafegegafafajljfoajflafjoajfafeg[pamnv;avafafaegaga";
        try {
            String encrypted = CryptoUtil.encryptAES(value, key.getBytes(StandardCharsets.UTF_8));
            Assert.assertNotNull(encrypted);
            Assert.assertNotEquals(encrypted, "");
            System.out.println(encrypted);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testDecrypt() {
        String key = "r343tere2es!ta@r";
        String value = "fafafafegegafafajljfoajflafjoajfafeg[pamnv;avafafaegaga";
        try {
            String encrypted = CryptoUtil.encryptAES(value, key.getBytes(StandardCharsets.UTF_8));
            Assert.assertNotNull(encrypted);
            Assert.assertNotEquals(encrypted, "");
            String decrypt = CryptoUtil.decryptAES(encrypted, key.getBytes(StandardCharsets.UTF_8));
            Assert.assertEquals(value, decrypt);
            System.out.println(decrypt);
        } catch (Exception e) {
            Assert.fail();
        }
    }
}
