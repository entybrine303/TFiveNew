package graduate.utils;

import java.util.Random;

public class RamdomID {
	public static String generateRandomId() {
        Random random = new Random();
        StringBuilder randomString = new StringBuilder(8);

        for (int i = 0; i < 8; i++) {
            int digit = random.nextInt(10); // Số từ 0 đến 9
            randomString.append(digit);
        }
        return randomString.toString();
    }
}
