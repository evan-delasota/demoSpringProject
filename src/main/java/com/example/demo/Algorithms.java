package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class Algorithms {
    // Bottom up Caching
    // Time  Complexity: O(n)
    // Space Complexity: O(n)
    public ArrayList<Integer> dynamicFib(int num) {
        ArrayList<Integer> fib = new ArrayList<Integer>();
        fib.add(0);
        fib.add(1);

        for (int i = 2; i <= num; ++i) {
            fib.add(fib.get(i - 1) + fib.get(i - 2));
        }

        return fib;
    }
    // Sieve
    // Time complexity: O(n log * log (n))
    public boolean isPrime(int num) {
        if (num <= 2) {
            return false;
        }
        int [] prime = new int[num + 1];
        for (int i = 3; i <= num; i += 2) {
            prime[i] = 1;
        }
        for (int i = 3; i <= num; i += 2) {
            if (prime[i] == 1) {
                for (int j = i * i; j <= num; j += i * 2) {
                    prime[j] = 0;
                }
            }
        }

        return prime[num] == 1;
    }
}
