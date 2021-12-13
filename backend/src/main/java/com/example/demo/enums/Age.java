package com.example.demo.enums;

import java.util.HashMap;
import java.util.Map;

public enum Age {
    TOTAL(0),
    VAP(1);

    private int value;
    private static Map map = new HashMap<>();

    private Age(int value) {
        this.value = value;
    }

    static {
        for (Age pageType : Age.values()) {
            map.put(pageType.value, pageType);
        }
    }

    public static Age valueOf(int pageType) {
        return (Age) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
