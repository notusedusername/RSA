package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GCD {
    private static Logger logger = LoggerFactory.getLogger(GCD.class);
    private long counter;
    private long divisor;
    private long result;
    private long[] x;
    private long[] y;
    private long iteration;

    public GCD(long counter, long divisor) {
        this.counter = counter;
        this.divisor = divisor;
        result = 0;
        iteration = 1;
        x = new long[]{1, 0, 0};
        y = new long[]{0, 1, 0};
    }

    public long calculateGCD() {
        return calculate();
    }

    private long calculate() {
        logger.info("");

        while (true) {
            result = counter / divisor;
            long newCounter = divisor;
            long previousDivisor = divisor;
            divisor = counter % divisor;

            if (divisor == 0) {
                divisor = previousDivisor;
                return previousDivisor;
            }
            if (iteration == 1) {
                countNewXY(x, y);
            } else if (iteration > 1) {
                shiftXY(x, y);
                countNewXY(x, y);
            }
            counter = newCounter;

            iteration++;


        }
    }

    private void countNewXY(long[] x, long[] y) {
        x[2] = x[1] * result + x[0];
        y[2] = y[1] * result + y[0];
    }

    private void shiftXY(long[] x, long[] y) {
        x[0] = x[1];
        x[1] = x[2];
        x[2] = 0;

        y[0] = y[1];
        y[1] = y[2];
        y[2] = 0;
    }

    public long getGCD() {
        return divisor;
    }

    public long getX() {
        x[2] = (int) (x[2] * Math.pow(-1, iteration));
        return x[2];
    }

    public long getY() {
        y[2] = (int) (y[2] * Math.pow(-1, iteration + 1));
        return y[2];
    }

    public long getIteration() {
        return iteration;
    }
}
