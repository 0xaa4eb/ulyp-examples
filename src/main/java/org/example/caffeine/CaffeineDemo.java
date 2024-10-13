package org.example.caffeine;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.time.Duration;

public class CaffeineDemo {

    public static void main(String[] args) {
        DatabaseJDBCSource source = new DatabaseJDBCSource();

        LoadingCache<Integer, DatabaseEntity> cache = Caffeine.newBuilder()
                .maximumSize(10_000)
                .expireAfterWrite(Duration.ofMinutes(5))
                .refreshAfterWrite(Duration.ofMinutes(1))
                .build(source::findById);

        DatabaseEntity fromCache = cache.get(5);

        System.out.println(fromCache);
    }
}
