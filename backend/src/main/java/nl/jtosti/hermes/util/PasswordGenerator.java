package nl.jtosti.hermes.util;

import java.security.SecureRandom;
import java.util.Random;

public class PasswordGenerator {
    private static final int LENGTH = 20;
    private static final char[] SYMBOLS = ("^$*.[]{}()?-\"!@#%&/\\,><':;|_~`").toCharArray();
    private static final char[] LOWERCASE = ("abcdefghijklmnopqrstuvwxyz").toCharArray();
    private static final char[] UPPERCASE = ("ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
    private static final char[] NUMBERS = ("0123456789").toCharArray();
    private static final char[] ALL_CHARS = ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789^$*.[]{}()?-\"!@#%&/\\,><':;|_~`").toCharArray();
    private static Random rand = new SecureRandom();

    private PasswordGenerator() {
    }

    public static String getPassword(int length) {
        if (length <= 4) {
            throw new IllegalArgumentException("Password length must be bigger than 4");
        }
        char[] password = new char[length];
        //get the requirements out of the way
        password[0] = LOWERCASE[rand.nextInt(LOWERCASE.length)];
        password[1] = UPPERCASE[rand.nextInt(UPPERCASE.length)];
        password[2] = NUMBERS[rand.nextInt(NUMBERS.length)];
        password[3] = SYMBOLS[rand.nextInt(SYMBOLS.length)];

        //populate rest of the password with random chars
        for (int i = 4; i < length; i++) {
            password[i] = ALL_CHARS[rand.nextInt(ALL_CHARS.length)];
        }

        //shuffle it up
        for (int i = 0; i < password.length; i++) {
            int randomPosition = rand.nextInt(password.length);
            char temp = password[i];
            password[i] = password[randomPosition];
            password[randomPosition] = temp;
        }

        return new String(password);
    }

    public static String generatePassword() {
        return getPassword(LENGTH);
    }
}
