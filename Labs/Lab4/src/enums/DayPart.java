package enums;

public enum DayPart {
    MORNING,
    AFTERNOON,
    EVENING,
    NIGHT;

    public String getString() {
        switch (this) {
            case MORNING:
                return "Morning";
            case AFTERNOON:
                return "Afternoon";
            case EVENING:
                return "Evening";
            default:
                return "Night";
        }
    }
}
