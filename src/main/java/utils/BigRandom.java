package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;
import java.util.Random;

public class BigRandom {
    private static Logger logger = LoggerFactory.getLogger(BigRandom.class);
    private long bigRandom;
    private Random random;

    public BigRandom() {
        random = new Random();
        do {
            bigRandom = Math.abs(createSecureRandomNumber());
            enlargeRandomNumber();
        }
        while (bigRandom > Config.getBigrandomUpperEdge());
    }

    public long getBigRandom(long bound) {
        return bigRandom - bound;
    }

    public long getBigRandom() {
        return bigRandom;
    }

    private void enlargeRandomNumber() {
        double multipler = Math.pow(10, random.nextInt(100 - 5) + 5);
        this.bigRandom = (long) (bigRandom / createRandomLessThan(10000) * multipler);
        this.bigRandom += createRandomLessThan(10000);
    }

    private byte createSecureRandomNumber() {
        SecureRandom sc = new SecureRandom();
        byte[] number = new byte[1];
        sc.nextBytes(number);
        return number[0];
    }

    private Integer createRandomLessThan(int max) {
        Integer createdRandom = random.nextInt(max);
        if (createdRandom == 0) {
            createdRandom = createRandomLessThan(max);
        }
        return createdRandom;
    }

}

