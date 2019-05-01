package utils;

import java.security.SecureRandom;
import java.util.Random;

public class BigRandom {
    private double bigRandom;
    private Random random;

    public BigRandom() {
        random = new Random();
        bigRandom = createSecureRandomNumber();
        enlargeRandomNumber(bigRandom);

    }

    public int getBigRandom() {
        return (int) Math.abs(bigRandom);
    }

    private void enlargeRandomNumber(double bigRandom) {
        double multipler = Math.pow(10, random.nextInt(10 - 5) + 5);
        this.bigRandom = bigRandom / createRandomLessThan(10) * multipler;
        this.bigRandom += createRandomLessThan(1000);
    }

    private byte createSecureRandomNumber() {
        SecureRandom sc = new SecureRandom();
        byte[] number = new byte[1];
        sc.nextBytes(number);
        return number[0];
    }

    private int createRandomLessThan(int max) {
        return random.nextInt(max);
    }

}

