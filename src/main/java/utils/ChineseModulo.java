package utils;

public class ChineseModulo {

    private Double modClass;
    private Double[] modulos;
    private Double[] remainders;
    private Double[] y;
    private Double[] iteratedM;
    private Double M = 1.0;
    private Double X = 0.0;

    public ChineseModulo(Double[] remainders, Double[] modulos) {
        this.remainders = remainders;
        this.modulos = modulos;
        this.y = new Double[modulos.length];
        iteratedM = initializeiteratedM(modulos.length);
        calculateModClass();
    }

    public Double getModClass() {
        return modClass;
    }

    private Double[] initializeiteratedM(int arraySize) {
        Double[] toReturn = new Double[arraySize];
        for (int i = 0; i < arraySize; i++) {
            toReturn[i] = 1.0;
        }
        return toReturn;
    }

    private void calculateModClass() {
        for (int i = 0; i < modulos.length; i++) {
            M = M * modulos[i];
            for (Double modulo : modulos) {
                iteratedM[i] *= modulo;
            }
            iteratedM[i] = iteratedM[i] / modulos[i];
            y[i] = modulo(iteratedM[i], modulos[i]);
            X += y[i] * remainders[i] * iteratedM[i];
        }
        modClass = X % M;
    }

    public static Double modulo(Double leftSide, Double modulo) {
        Double rightSide = 1.0;
        while (rightSide % leftSide != 0) {
            rightSide += modulo;
        }
        return rightSide / leftSide;
    }

}
