package world.inetum.realdolmen.realjobs.utility;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class GenerateID {
    private final String characters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final Random random = new SecureRandom();

    private String generateRandomString(int length) {
        StringBuilder randomString = new StringBuilder(length);
        for (int i = 0; i <= length; i++) {
            randomString.append(characters.charAt(random.nextInt(characters.length())));
        }
        return new String(randomString);
    }

    public String generateId(int length) {
        return generateRandomString(length);
    }
}
