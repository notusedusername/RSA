import utils.BigRandom;
import utils.ChineseModulo;
import utils.MillerRabin;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        /*
        int i = 0;
        while (i != 2){
            boolean sth = new MillerRabin().isPrime(new BigRandom().getBigRandom());
            System.out.println(sth);

            if(sth) {
                i++;
            }
        }

         */
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(new MillerRabin().isPrime(scanner.nextInt()));
        }
    }
}
