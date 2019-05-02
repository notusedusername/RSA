package utils;

public class Config {
    private static long bigrandomUpperEdge = 2147483647;
    private static int NrOfTests = 1;

    public static int getNrOfTests() {
        return NrOfTests;
    }

    public static int setNrOfTests(int nrOfTests) {
        return NrOfTests = nrOfTests;
    }

    public static void setBigrandomUpperEdge(long bigrandomUpperEdge) {
        Config.bigrandomUpperEdge = bigrandomUpperEdge;
    }

    public static long getBigrandomUpperEdge() {
        return bigrandomUpperEdge;
    }

}
