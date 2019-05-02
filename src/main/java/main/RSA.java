package main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.*;

import java.util.Random;

public class RSA {

    private static Logger logger = LoggerFactory.getLogger(RSA.class);
    private Double n;
    private Double modifiedN;
    private Double p;
    private Double q;
    private Double e;
    private Double d;

    public RSA() {
        p = randomBigPrime();
        q = notTheSame(randomBigPrime());
        n = p * q;
        modifiedN = (p - 1) * (q - 1);
        e = randomRelativePrime();
    }

    public Double[] getPublicKey() {
        return new Double[]{e, n};
    }

    public Double[] getPrivateKey() {
        return new Double[]{d, n};
    }

    public Double encrypt(Double message) {
        makePrivateKey();
        return FastPow.pow(message, e, n); //// FIXME: 2019.05.02. see log.txt

    }

    public Double decrypt(Double encryptedMessage) {
        return FastPow.pow(encryptedMessage, d, n); // FIXME: 2019.05.02. see log.txt
    }

    private void makePrivateKey() {
        d = ChineseModulo.modulo(e, modifiedN);
    }

    private Double notTheSame(Double currentRandomPrime) {
        if (p.equals(currentRandomPrime)) {
            return notTheSame(randomBigPrime());
        } else {
            return currentRandomPrime;
        }
    }

    private Double randomBigPrime() {
        Double toReturn;
        logger.info("Getting random big prime");
        while (true) {
            toReturn = new BigRandom().getBigRandom();
            if (toReturn.isInfinite() || toReturn.isNaN()) {
                //continue;
            } else {
                if (new MillerRabin().isPrime(toReturn)) {
                    logger.info("Found a random big prime");
                    return toReturn;
                }
            }
        }
    }

    private Double randomRelativePrime() {
        logger.info("");
        while (true) {
            Double randomValue = (double) new Random().nextInt(1000);
            if (randomValue % 2 == 1 && new GCD(n.intValue(), randomValue.intValue()).calculateGCD() == 1) {
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
