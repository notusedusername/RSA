package utils;

public class ChineseModulo {

    private int modClass;
    private int[] modulos;
    private int[] remainders;
    private int[] y;
    private int[] iteratedM;
    private int M = 1;
    private int X = 0;

    public ChineseModulo(int[] remainders, int[] modulos) {
        this.remainders = remainders;
        this.modulos = modulos;
        this.y = new int[modulos.length];
        iteratedM = initializeiteratedM(modulos.length);
        calculateModClass();
    }

    public int getModClass() {
        return modClass;
    }

    private int[] initializeiteratedM(int arraySize) {
        int[] toReturn = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            toReturn[i] = 1;
        }
        return toReturn;
    }

    private void calculateModClass() {
        for (int i = 0; i < modulos.length; i++) {
            M = M * modulos[i];
            for (int j = 0; j < modulos.length; j++) {
                iteratedM[i] *= modulos[j];
            }
            iteratedM[i] = iteratedM[i] / modulos[i];
            System.out.println(iteratedM[i]);
            y[i] = iteratedM[i] % modulos[i];
            X += y[i] * remainders[i] * iteratedM[i];
        }
        modClass = X % M;
    }

}
