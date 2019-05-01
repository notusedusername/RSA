package utils;

import java.util.Random;

public class MillerRabin {
    private int testSubject;
    private int factorizedTwoPow;
    private int remainder;

    public boolean isPrime(int testSubject) {
        System.out.println(testSubject);
        this.testSubject = testSubject;
        factorizedTwoPow = powOfTwo(testSubject - 1);
        if (testSubject <= 1) {
            return false;
        }
        return testTheNumber();
    }

    private boolean testTheNumber() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int randomTester = random.nextInt(testSubject - 1) + 1;
            int randomTwoPow;
            if (factorizedTwoPow != 0) {
                randomTwoPow = new Random().nextInt(factorizedTwoPow);
            } else {
                randomTwoPow = 0;
            }
            if (!(Math.pow(randomTester, remainder) % testSubject == 1
                    || Math.pow(randomTester, Math.pow(2, randomTwoPow) * remainder) % testSubject == testSubject - 1)) {
                return false;
            }
        }
        return true;
    }


    private int powOfTwo(int number) {
        int TwoCounter = 0;
        while (number % 2 == 0) {
            TwoCounter++;
            number /= 2;
        }
        calculateRemainder(number);
        return TwoCounter;
    }

    private void calculateRemainder(int number) {
        remainder = (int) ((testSubject - 1) / Math.pow(2, factorizedTwoPow));
    }
}
