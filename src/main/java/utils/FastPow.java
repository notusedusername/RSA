package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;

public class FastPow {

    private static Logger logger = LoggerFactory.getLogger(FastPow.class);
    private static long base;
    private static Long power;
    private static String binaryPower;
    private static long modulo;
    private static long[] squares;

    private static void initializeObject(long base, Long power, long modulo) {
        FastPow.base = base;
        FastPow.power = power;
        FastPow.modulo = modulo;
        FastPow.binaryPower = convertToBinary(power);
        FastPow.squares = new long[binaryPower.length()];
        squares[binaryPower.length() - 1] = base;
    }

    public static long pow(long base, Long power, long modulo) {
        initializeObject(base, power, modulo);
        return calculateSquares();
    }

    private static long calculateSquares() {
        BigInteger result = BigInteger.ONE;
        for (int i = binaryPower.length() - 1; i >= 0; i--) {
            if (i != binaryPower.length() - 1) {
                squares[i] = (long) Math.pow(squares[i + 1], 2) % modulo;
            }
            if (binaryPower.charAt(i) == '1') {
                result = result.multiply(new BigInteger(String.valueOf(squares[i])));
            }
        }
        result = result.mod(new BigInteger(String.valueOf(modulo)));
        return result.longValue();
    }

    private static String convertToBinary(Long power) {
        return Integer.toBinaryString(power.intValue());
    }
}
