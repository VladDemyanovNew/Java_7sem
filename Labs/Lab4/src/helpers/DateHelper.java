package helpers;

import java.util.*;
import enums.*;

public class DateHelper {
    public static DayPart getDayPart() {
        int currentHours = new Date().getHours();
        if (currentHours >= 5 && currentHours <= 12) {
            return DayPart.MORNING;
        } else if (currentHours >= 13 && currentHours <= 16) {
            return DayPart.AFTERNOON;
        } else if (currentHours >= 17 && currentHours <= 23) {
            return DayPart.EVENING;
        } else {
            return DayPart.NIGHT;
        }
    }
}