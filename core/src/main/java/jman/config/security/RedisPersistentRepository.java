package com.jicg.jman.config.security;

import cn.hutool.core.util.StrUtil;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author jicg on 2020/9/14
 */
@Component
public class RedisPersistentRepository implements PersistentTokenRepository {
    private final static String USERNAME_KEY = "spring:security:rememberMe:USERNAME_KEY:";
    private final static String SERIES_KEY = "spring:security:rememberMe:SERIES_KEY:";
    final RedisTemplate<String, Object> redisTemplate;

    final StringRedisTemplate stringRedisTemplate;

    public RedisPersistentRepository(RedisTemplate<String, Object> redisTemplate, StringRedisTemplate stringRedisTemplate) {
        this.redisTemplate = redisTemplate;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void createNewToken(PersistentRememberMeToken persistentRememberMeToken) {
        String series = persistentRememberMeToken.getSeries();
        String key = generateKey(series, SERIES_KEY);
        String usernameKey = generateKey(persistentRememberMeToken.getUsername(), USERNAME_KEY);
        //用户只要采用账户密码重新登录，那么为了安全就有必要清除之前的token信息。deleteIfPresent方法通过
        //username查找到用户对应的series，然后删除旧的token信息。
        deleteIfPresent(usernameKey);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("username", persistentRememberMeToken.getUsername());
        hashMap.put("token", persistentRememberMeToken.getTokenValue());
        hashMap.put("date", String.valueOf(persistentRememberMeToken.getDate().getTime()));
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        hashOperations.putAll(key, hashMap);
        redisTemplate.expire(key, 1, TimeUnit.DAYS);//设置token保存期限
        stringRedisTemplate.opsForValue().set(usernameKey, series);
        redisTemplate.expire(usernameKey, 1, TimeUnit.DAYS);
    }

    @Override
    public void updateToken(String s, String s1, Date date) {
        String key = generateKey(s, SERIES_KEY);
        if (redisTemplate.hasKey(key))
            redisTemplate.opsForHash().put(key, "token", s1);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String s) {
        String key = generateKey(s, SERIES_KEY);
        List<Object> hashKeys = new ArrayList<>();
        hashKeys.add("username");
        hashKeys.add("token");
        hashKeys.add("date");
        List<Object> hashValuesObj = redisTemplate.opsForHash().multiGet(key, hashKeys);
        List<String> hashValues = hashValuesObj.stream().map(StrUtil::toString).collect(Collectors.toList());
        String username = hashValues.get(0);
        String tokenValue = hashValues.get(1);
        String date = hashValues.get(2);
        if (null == username || null == tokenValue || null == date||date.equalsIgnoreCase("null")) {
            return null;
        }
        Long timestamp = Long.valueOf(date);
        Date time = new Date(timestamp);
        return new PersistentRememberMeToken(username, s, tokenValue, time);
    }

    @Override
    public void removeUserTokens(String s) {
        //rememberMeService实现类中调用这个方法传入的参数是username，因此我们必须通过username查找到
        //对应的series，然后再通过series查找到对应的token信息再删除。
        String key = generateKey(s, USERNAME_KEY);
        deleteIfPresent(key);
    }

    private void deleteIfPresent(String key) {
        //删除token时应该同时删除token信息，以及保存了对应的username与series对照数据。
        if (!redisTemplate.hasKey(key)) {
            return;
        }
        String series = generateKey(stringRedisTemplate.opsForValue().get(key), SERIES_KEY);
        if (series != null && redisTemplate.hasKey(series)) {
            redisTemplate.delete(series);
            redisTemplate.delete(key);
        }
    }

    private String generateKey(String series, String prefix) {
        return prefix + series;
    }
}
