package main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.*;

import java.util.Random;

public class RSA {

    private static Logger logger = LoggerFactory.getLogger(RSA.class);
    private long n;
    private long modifiedN;
    private long p;
    private long q;
    private long e;
    private long d;

    public RSA() {
        p = randomBigPrime();
        q = notTheSame(randomBigPrime());
        n = p * q;
        modifiedN = (p - 1) * (q - 1);
        e = randomRelativePrime();
    }

    public long[] getPublicKey() {
        return new long[]{e, n};
    }

    public long[] getPrivateKey() {
        return new long[]{d, n};
    }

    public long encrypt(long message) {
        makePrivateKey();
        return FastPow.pow(message, e, n);

    }

    public long decrypt(long encryptedMessage) {
        return FastPow.pow(encryptedMessage, d, n);
    }

    private void makePrivateKey() {
        d = ChineseModulo.modulo(e, 1, modifiedN);
        logger.info("d value {}", d);
    }

    private long notTheSame(long currentRandomPrime) {
        logger.trace("search not the same prime");
        while (true) {
            if (p != currentRandomPrime) {
                return currentRandomPrime;
            } else {
                currentRandomPrime = randomBigPrime();
            }
        }
    }

    private long randomBigPrime() {
        long toReturn;
        logger.info("Getting random big prime");
        while (true) {
            toReturn = new BigRandom().getBigRandom();
            logger.trace("{}", toReturn);
            if (new MillerRabin().isPrime(toReturn)) {
                logger.info("Found a random big prime");
                return toReturn;
            }
        }
    }

    private long randomRelativePrime() {
        logger.info("");
        while (true) {
            long randomValue = (long) new Random().nextInt(1000);
            if (randomValue % 2 == 1 && new GCD(modifiedN, randomValue).calculateGCD() == 1) {
                logger.info("Relative prime found");
                return randomValue;
            }
        }
    }

    @Override
    public String toString() {
        return "RSA{" +
                "n=" + n +
                ", modifiedN=" + modifiedN +
                ", p=" + p +
                ", q=" + q +
                ", e=" + e +
                ", d=" + d +
                '}';
    }
}
