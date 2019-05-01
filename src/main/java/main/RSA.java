package main;

import utils.BigRandom;
import utils.ChineseModulo;
import utils.GCD;
import utils.MillerRabin;

import java.util.Random;

public class RSA {

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

    private Double[] getPrivateKey() {
        return new Double[]{d, n};
    }

    public Double encrypt(Integer message) {
        makePrivateKey();
        return Math.pow(message, e);//todo gyors hatványzás

    }

    public Double decrypt(Integer encryptedMessage) {
        return null; //todo decryption
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
        while (true) {
            toReturn = new BigRandom().getBigRandom();
            if (toReturn.isInfinite() || toReturn.isNaN()) {
                continue;
            } else {
                if (new MillerRabin().isPrime(toReturn)) {
                    return toReturn;
                }
            }
        }
    }

    private Double randomRelativePrime() {
        while (true) {
            Double randomValue = (double) new Random().nextInt(1000);
            if (randomValue % 2 == 1 && new GCD(n.intValue(), randomValue.intValue()).calculateGCD() == 1) {
                return randomValue;
            }
        }
    }

}
