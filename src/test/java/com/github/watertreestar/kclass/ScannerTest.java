package com.github.watertreestar.kclass;

import com.github.watertreestar.klass.Scanner;
import org.junit.Test;

import java.util.List;

public class ScannerTest {
    @Test
    public void test(){
        Scanner scanner = new Scanner("com.github,org.junit");
        List<String> result = scanner.scan();
        result.stream().forEach(System.out::println);
    }
}
