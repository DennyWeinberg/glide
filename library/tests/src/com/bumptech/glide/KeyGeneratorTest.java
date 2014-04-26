package com.bumptech.glide;

import android.test.AndroidTestCase;
import com.bumptech.glide.resize.SafeKeyGenerator;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KeyGeneratorTest extends AndroidTestCase {
    private SafeKeyGenerator keyGenerator;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        keyGenerator = new SafeKeyGenerator();
    }

    public void testKeysAreValidForDiskCache() {
        String key;
        final Pattern diskCacheRegex = Pattern.compile("[a-z0-9_-]{64}");
        for (int i = 0; i < 1000; i++) {
            key = getRandomKeyFromGenerator();
            final Matcher matcher = diskCacheRegex.matcher(key);
            assertTrue(matcher.matches());
        }
    }

    private String getRandomKeyFromGenerator() {
        return keyGenerator.getSafeKey(getRandomId());
    }

    private static String getRandomId() {
        return UUID.randomUUID().toString();
    }
}
