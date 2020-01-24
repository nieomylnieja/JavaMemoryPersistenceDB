package model;

import utils.EmployeePayload;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;
import java.util.Scanner;

public class Admin extends Employee {
    private static final SecureRandom RANDOM = new SecureRandom();
    private SecretKey secretKey;
    private String password;


    public Admin(EmployeePayload payload, int keyBitSize, String algName) throws NoSuchAlgorithmException {
        super(payload);
        this.secretKey = generateSecretKey(keyBitSize, algName);
        System.out.printf("Secret key generated successfully!" +
                "Base64 encoded: %s\n" +
                "Be sure to write it down!\n", decodedSecretKey());
    }

    private static SecretKey generateSecretKey(int keyBitSize, String algName) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = initializeKeyGenerator(keyBitSize, algName);
        return keyGenerator.generateKey();
    }

    private static KeyGenerator initializeKeyGenerator(int keyBitSize, String algName) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algName);
        keyGenerator.init(keyBitSize, RANDOM);
        return keyGenerator;
    }

    public String getSecretKeyAlgo() {
        return this.secretKey.getAlgorithm();
    }

    public boolean getPassword() throws SecurityException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please provide a password:");
        int attempts = 0;
        while (attempts < 3) {
            if (scan.nextLine().equals(this.password)) {
                return true;
            }
            System.out.printf("You have %d more attempts left!\n", 3 - attempts);
            attempts++;
        }
        throw new SecurityException("password breach attempt!");
    }

    public Optional<String> getDecodedSecretKey() throws SecurityException {
        if (getPassword()) {
            return Optional.of(decodedSecretKey());
        };
        return Optional.empty();
    }

    private String decodedSecretKey() {
        return Base64.getEncoder().encodeToString(this.secretKey.getEncoded());
    }

    public void setPassword() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please provide a one time password you will use to access employees data and the secret key");
        this.password = scan.nextLine();
    }

    public void printEmployeeData() {
        this.printBasicInfo();
        System.out.printf(" - Secret key ciphered with: %s\n\n", this.getSecretKeyAlgo());
    }
}
