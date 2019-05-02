package main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ChineseModulo;

import java.util.Scanner;

public class Main {
    private static Logger logger = LoggerFactory.getLogger(ChineseModulo.class);

    private static int message;

    private static void getMessageFromUser() {
        System.out.println("\tRSA encryptor/decryptor 1.0alpha" +
                "\n\t---------------------------------" +
                "\nÍrd be az üzenetet egy egész szám formájában:");
        Scanner scanner = new Scanner(System.in);
        Main.message = scanner.nextInt();
    }

    public static void testRSA(int NrOfTests) {
        long decryptedMessage;
        int iteration = 0;
        do {
            RSA rsa = new RSA();
            logger.info(rsa.toString());
            logger.info("Encryption started");
            long encryptedMessage = rsa.encrypt(message);
            logger.info("Encryption done");
            logger.info("Decryption started");
            decryptedMessage = rsa.decrypt(encryptedMessage);
            logger.info("Decryption done");
            System.out.println("Eredeti üzenet: " + message
                    + "\nTitkosított üzenet: " + encryptedMessage
                    + "\nVisszafejtett üzenet: " + decryptedMessage);
            iteration++;
            logger.warn("{}", iteration);
        }
        while (decryptedMessage == message && iteration < NrOfTests);

        if (decryptedMessage != message) {
            logger.error("The 2 message is not the same!");
        } else {
            logger.info("All tests was succesful.");
        }
    }

    public static void main(String[] args) {
        getMessageFromUser();
        testRSA(1);

    }
}
