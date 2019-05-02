package utils;

import main.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChineseModulo {

    private static Logger logger = LoggerFactory.getLogger(ChineseModulo.class);
    private long modClass;
    private long[] modulos;
    private long[] remainders;
    private long[] y;
    private long[] iteratedM;
    private long M = 1;
    private long X = 0;

    public ChineseModulo(long[] remainders, long[] modulos) {
        this.remainders = remainders;
        this.modulos = modulos;
        this.y = new long[modulos.length];
        iteratedM = initializeiteratedM(modulos.length);
        calculateModClass();
    }

    public long getModClass() {
        return modClass;
    }

    private long[] initializeiteratedM(int arraySize) {
        long[] toReturn = new long[arraySize];
        for (int i = 0; i < arraySize; i++) {
            toReturn[i] = 1;
        }
        return toReturn;
    }

    private void calculateModClass() {
        logger.info("");
        for (int i = 0; i < modulos.length; i++) {
            M = M * modulos[i];
            for (long modulo : modulos) {
                iteratedM[i] *= modulo;
            }
            iteratedM[i] = iteratedM[i] / modulos[i];
            y[i] = modulo(iteratedM[i], remainders[i], modulos[i]);
            X += y[i] * remainders[i] * iteratedM[i];
        }
        modClass = X % M;
    }

    public static long modulo(long leftSide, long rightSide, long modulo) {
        logger.info("Calculate linear congruence {} 1 (mod {})", leftSide, modulo);
        if (rightSide % new GCD(leftSide, modulo).calculateGCD() == 0) {
            while (rightSide % leftSide != 0) {
                rightSide += modulo;
                logger.trace("{}= {} (mod {})", leftSide, rightSide, modulo);
            }
            return rightSide / leftSide;

        } else {
            logger.error("The congruence cannot be solved!");
            System.out.println("Please restart the algorithm!");
            Main.testRSA(Config.getNrOfTests());
            System.exit(0);
        }
        return 0;
    }

}
