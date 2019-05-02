package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class MillerRabin {
    private static Logger logger = LoggerFactory.getLogger(MillerRabin.class);
    private Long testSubject;
    private int factorizedTwoPow;
    private long remainder;

    public boolean isPrime(long testSubject) {
        if (testSubject <= 1 || testSubject % 2 == 0) {
            return testSubject == 2;
        }
        this.testSubject = testSubject;
        powOfTwo(testSubject - 1);
        return testTheNumber();
    }

    private boolean testTheNumber() {
        for (int i = 0; i < 100; i++) {
            long randomTester = createNewRandomTester();
            if (!(FastPow.pow(randomTester, remainder, testSubject) == 1
                    || searchForTrueSecondCondition(randomTester))) {
                return false;
            }
        }
        return true;
    }

    private long createNewRandomTester() {
        Random random = new Random();
        long createdRandom;
        do {
            createdRandom = Math.abs(new BigRandom().getBigRandom(testSubject));
        } while (createdRandom == 0 || createdRandom >= testSubject.intValue());
        logger.trace("Apu nézd: {} < {}", createdRandom, testSubject.intValue());
        return createdRandom;
    }

    private boolean isThisAGoodRandom(long randomTester, long r) {
        logger.trace("{}^{}^{} * {} % {} == {}", randomTester, 2, r, remainder, testSubject, testSubject - 1);
        return FastPow.pow(randomTester, (long) Math.pow(2, r) * remainder, testSubject) == testSubject - 1;
    }

    private boolean searchForTrueSecondCondition(long randomTester) {
        for (int i = 0; i <= factorizedTwoPow; i++) {
            if (isThisAGoodRandom(randomTester, i)) {
                return true;
            }
        }
        return false;
    }


    private void powOfTwo(long number) {
        int TwoCounter = 0;
        while (number % 2 == 0) {
            TwoCounter++;
            number /= 2;
        }
        factorizedTwoPow = TwoCounter;
        remainder = number;
        logger.trace("Kettő hatványok a {} számban: {}, t= {}", testSubject, factorizedTwoPow, number);
    }
}
