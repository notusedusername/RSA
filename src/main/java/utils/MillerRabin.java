package utils;

import java.util.Random;

public class MillerRabin {
    private Double testSubject;
    private int factorizedTwoPow;
    private Double remainder;

    public boolean isPrime(Double testSubject) {
        if (testSubject <= 1 || testSubject % 2 == 0) {
            return testSubject == 2;
        }
        this.testSubject = testSubject;
        powOfTwo(testSubject - 1);
        return testTheNumber();
    }

    private boolean testTheNumber() {
        Random random = new Random();
        boolean foundAGoodRandom;
        for (int i = 0; i < 10; i++) {
            Integer randomTester = random.nextInt(testSubject.intValue() - 1) + 1;
            int randomTwoPow;
            if (factorizedTwoPow != 0) {
                randomTwoPow = new Random().nextInt(factorizedTwoPow);
            } else {
                randomTwoPow = 0;
            }
            foundAGoodRandom = isThisAGoodRandom(randomTester, randomTwoPow);

            if (!(Math.pow(randomTester, remainder) % testSubject == 1)) {
                if (foundAGoodRandom) {
                    return true;
                } else {
                    return searchForTrueSecondCondition(randomTester);
                }
            }

        }
        return true;
    }

    private boolean isThisAGoodRandom(Integer randomTester, Integer randomTwoPow) {
        return Math.pow(randomTester, Math.pow(2, randomTwoPow) * remainder) % testSubject == (testSubject - 1);
    }

    private boolean searchForTrueSecondCondition(Integer randomTester) {
        for (int i = 0; i < factorizedTwoPow; i++) {
            if (isThisAGoodRandom(randomTester, i)) {
                return true;
            }
        }
        return false;
    }


    private void powOfTwo(Double number) {
        int TwoCounter = 0;
        while (number % 2 == 0) {
            TwoCounter++;
            number /= 2;
        }
        factorizedTwoPow = TwoCounter;
        remainder = number;
    }
}
