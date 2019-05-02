package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FastPow {

    private static Logger logger = LoggerFactory.getLogger(FastPow.class);
    private static Double base;
    private static Double power;
    private static String binaryPower;
    private static Double modulo;
    private static Double[] squares;

    private static void initializeObject(Double base, Double power, Double modulo) {
        FastPow.base = base;
        FastPow.power = power;
        FastPow.modulo = modulo;
        FastPow.binaryPower = convertToBinary(power);
        FastPow.squares = new Double[binaryPower.length()];
        squares[binaryPower.length() - 1] = base;
    }

    public static Double pow(Double base, Double power, Double modulo) {
        initializeObject(base, power, modulo);
        Double result = calculateSquares();
        return result % modulo;
    }

    private static Double calculateSquares() {
        Double result = 1.0;
        for (int i = binaryPower.length() - 1; i >= 0; i--) {
            if (i != binaryPower.length() - 1) {
                squares[i] = Math.pow(squares[i + 1], 2) % modulo;
            }
            if (binaryPower.charAt(i) == '1') {
                result *= squares[i];
            }
        }
        return result;
    }

    private static String convertToBinary(Double power) {
        return Integer.toBinaryString(power.intValue());
    }
}
