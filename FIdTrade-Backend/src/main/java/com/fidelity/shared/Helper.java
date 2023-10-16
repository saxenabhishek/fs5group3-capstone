package com.fidelity.shared;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class Helper {
    public static BigDecimal makeBigDecimal(String value) {
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_DOWN);
    }

     public static String generateRandomNumericString(int length) {
        String characters = "0123456789";
        
        StringBuilder randomString = new StringBuilder(length);

        // Create a random number generator
        Random random = new Random();

        // Generate random characters and append them to the StringBuilder
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            randomString.append(randomChar);
        }

        return randomString.toString();
    }
}
