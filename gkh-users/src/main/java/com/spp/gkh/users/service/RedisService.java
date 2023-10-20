package com.spp.gkh.users.service;

//@Service
public interface RedisService {

    <T> void set(String key, T value);

    <T> T get(String key);

    <T> T delete(String key);

}