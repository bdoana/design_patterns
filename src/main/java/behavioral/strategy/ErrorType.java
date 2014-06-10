package behavioral.strategy;

import java.util.*;

/**
 */
public enum ErrorType {
    BUSINESS(0, "B", "BUSINESS"),
    TECHNICAL(1, "T", "TECHNICAL");

    ErrorType(int value, String name, String descr) {
        this.value = value;
        this.name = name;
        this.descr = descr;
    }

    private static final Map<Integer, ErrorType> VALUES =
        new HashMap<Integer, ErrorType>();

    private final int value;
    private final String name;
    private final String descr;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public String getDescr() {
        return descr;
    }

    static {
        for(ErrorType type : values()) {
            VALUES.put(type.getValue(), type);
        }
    }

    public static ErrorType getValue(int value) {
        return VALUES.get(value);
    }
}
