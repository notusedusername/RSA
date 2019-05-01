package main;

public class Main {

    public static void main(String[] args) {
        RSA rsa = new RSA();
        int message = 2;
        Double encryptedMessage = rsa.encrypt(message);
        System.out.println("Eredeti üzenet: " + message
                + "\nTitkosított üzenet: " + encryptedMessage);
    }
}
