package utils;

public class GCD {

    private Integer counter;
    private Integer divisor;
    private Integer result;
    private Integer[] x;
    private Integer[] y;
    private Integer iteration;

    public GCD(Integer counter, Integer divisor) {
        this.counter = counter;
        this.divisor = divisor;
        result = 0;
        iteration = 1;
        x = new Integer[]{1, 0, 0};
        y = new Integer[]{0, 1, 0};
    }

    public Integer calculateGCD() {
        return calculate();
    }

    private Integer calculate() {
        while (true) {
            result = counter / divisor;
            Integer newCounter = divisor;
            Integer previousDivisor = divisor;
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

    private void countNewXY(Integer[] x, Integer[] y) {
        x[2] = x[1] * result + x[0];
        y[2] = y[1] * result + y[0];
    }

    private void shiftXY(Integer[] x, Integer[] y) {
        x[0] = x[1];
        x[1] = x[2];
        x[2] = 0;

        y[0] = y[1];
        y[1] = y[2];
        y[2] = 0;
    }

    public Integer getGCD() {
        return divisor;
    }

    public Integer getX() {
        x[2] = (int) (x[2] * Math.pow(-1, iteration));
        return x[2];
    }

    public Integer getY() {
        y[2] = (int) (y[2] * Math.pow(-1, iteration + 1));
        return y[2];
    }

    public Integer getIteration() {
        return iteration;
    }
}
