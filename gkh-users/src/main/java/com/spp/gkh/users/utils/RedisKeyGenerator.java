package com.spp.gkh.users.utils;

public class RedisKeyGenerator {
    public static String generateUserKey(Long userId) {
        return "user-" + userId;
    }
}
