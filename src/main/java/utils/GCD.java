package utils;

public class GCD {

    private int counter;
    private int divisor;
    private int result;
    private int[] x;
    private int[] y;
    private int iteration;

    public GCD(int counter, int divisor) {
        this.counter = counter;
        this.divisor = divisor;
        result = 0;
        iteration = 1;
        x = new int[]{1, 0, 0};
        y = new int[]{0, 1, 0};
    }

    public int calculateGCD() {
        return calculate();
    }

    private int calculate() {
        while (true) {
            result = counter / divisor;
            int newCounter = divisor;
            int previousDivisor = divisor;
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

    private void countNewXY(int[] x, int[] y) {
        x[2] = x[1] * result + x[0];
        y[2] = y[1] * result + y[0];
    }

    private void shiftXY(int[] x, int[] y) {
        x[0] = x[1];
        x[1] = x[2];
        x[2] = 0;

        y[0] = y[1];
        y[1] = y[2];
        y[2] = 0;
    }

    public int getGCD() {
        return divisor;
    }

    public int getX() {
        x[2] = (int) (x[2] * Math.pow(-1, iteration));
        return x[2];
    }

    public int getY() {
        y[2] = (int) (y[2] * Math.pow(-1, iteration + 1));
        return y[2];
    }

    public int getIteration() {
        return iteration;
    }
}
