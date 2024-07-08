package com.kick;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;

import com.kick.service.CategoryService;

@SpringBootTest
class KickAndRushApplicationTests {

    @Autowired
    private CategoryService categoryService;

    @Test
    void testCache() {
        // 첫 번째 호출: 데이터베이스에서 조회하고 캐시에 저장됨
        long startTime = System.currentTimeMillis();
        categoryService.categoryList();
        long endTime = System.currentTimeMillis();
        long durationWithoutCache = endTime - startTime;
        System.out.println("캐시 Duration without cache: " + durationWithoutCache + " ms");

        // 두 번째 호출: 캐시에서 조회
        startTime = System.currentTimeMillis();
        categoryService.categoryList();
        endTime = System.currentTimeMillis();
        long durationWithCache = endTime - startTime;
        System.out.println("Duration with cache: " + durationWithCache + " ms");

        // 캐시가 실제로 작동했는지 확인
        if (durationWithCache < durationWithoutCache) {
            System.out.println("Cache is working. Time saved: " + (durationWithoutCache - durationWithCache) + " ms");
        } else {
            System.out.println("Cache is not working as expected.");
        }

        // JUnit assertion
        assert durationWithCache < durationWithoutCache : "Cache should reduce the time for the second query";
    }
}