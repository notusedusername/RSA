package main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ChineseModulo;

public class Main {
    private static Logger logger = LoggerFactory.getLogger(ChineseModulo.class);

    public static void main(String[] args) {
        Double decryptedMessage = 2.;
        int iteration = 0;
        while (decryptedMessage == 2.) {
            RSA rsa = new RSA();
            Double message = 2.0;
            logger.info("Encryption started");
            Double encryptedMessage = rsa.encrypt(message);
            logger.info("Encryption done");
            logger.info("Decryption started");
            decryptedMessage = rsa.decrypt(encryptedMessage);
            logger.info("Decryption done");
            logger.info("Eredeti üzenet: " + message
                    + "\nTitkosított üzenet: " + encryptedMessage
                    + "\nVisszafejtett üzenet: " + decryptedMessage
                    + "\n " + rsa.toString());
            iteration++;
            logger.warn("{}", iteration);
        }
        logger.error("The 2 message is not the same!");
    }
}
