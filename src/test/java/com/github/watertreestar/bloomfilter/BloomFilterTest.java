package com.github.watertreestar.bloomfilter;

import com.google.common.hash.Funnels;
import org.junit.Before;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BloomFilterTest {
    private int total = 10000000; //测试元素的总数

    private List<String> existingElements = null;
    private List<String> nonExistingElements = null;

    private void printStat(long start, long end) {
        double diff = (end - start) / 1000.0;
        System.out.println(diff + "s, " + (total / diff) + " 元素/s");
    }

    @Before
    public void prepare() {

        final Random r = new Random();
        existingElements = new ArrayList(total);
        for (int i = 0; i < total; i++) {
            existingElements.add(Double.toString(r.nextDouble()));
        }

        nonExistingElements = new ArrayList(total);
        for (int i = 0; i < total; i++) {
            nonExistingElements.add(Double.toString(r.nextDouble()));
        }

    }

    @Test
    public void test() {
        double fpp = 0.001d;
        BloomFilter<String> bloomFilter = new BloomFilter(fpp, total);
        com.google.common.hash.BloomFilter<String> google_bf = com.google.common.hash.BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), total, fpp);

        // 添加元素
        System.out.print(" Bloom Filter添加元素: ");
        long start = System.currentTimeMillis();
        for (int i = 0; i < total; i++) {
            bloomFilter.add(existingElements.get(i));
        }
        long end = System.currentTimeMillis();
        printStat(start, end);

        System.out.print("Google Bloom Filter添加元素: ");
        start = System.currentTimeMillis();
        for (int i = 0; i < total; i++) {
            google_bf.put(existingElements.get(i));
        }
        end = System.currentTimeMillis();
        printStat(start, end);

        //测试已经存在的元素
        System.out.print("Bloom Filter测试已经存在的元素: ");
        start = System.currentTimeMillis();
        for (int i = 0; i < total; i++) {
            bloomFilter.contains(existingElements.get(i));
        }
        end = System.currentTimeMillis();
        printStat(start, end);

        System.out.print("Google Bloom Filter测试已经存在的元素: ");
        start = System.currentTimeMillis();
        for (int i = 0; i < total; i++) {
            bloomFilter.contains(existingElements.get(i));
        }
        end = System.currentTimeMillis();
        printStat(start, end);

        //测试不存在的元素
        System.out.print(" Bloom Filter 测试不存在的元素: ");
        start = System.currentTimeMillis();
        for (int i = 0; i < total; i++) {
            bloomFilter.contains(nonExistingElements.get(i));
        }
        end = System.currentTimeMillis();
        printStat(start, end);

        System.out.print("Google Bloom Filter 测试不存在的元素: ");
        start = System.currentTimeMillis();
        for (int i = 0; i < total; i++) {
            bloomFilter.contains(nonExistingElements.get(i));
        }
        end = System.currentTimeMillis();
        printStat(start, end);
    }

}
