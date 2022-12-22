package util;

import java.util.*;

public class XXRand {

    public static Integer Get(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
