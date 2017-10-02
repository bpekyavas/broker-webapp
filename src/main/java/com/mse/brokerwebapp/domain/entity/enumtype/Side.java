package com.mse.brokerwebapp.domain.entity.enumtype;

import java.util.HashMap;
import java.util.Map;

public enum Side {
    BUY(1),
    SELL(2);

    private int value;
    private static Map<Integer,Side> map = new HashMap<>();

    private Side(int value) {
        this.value = value;
    }

    static {
        for (Side side : Side.values()) {
            map.put(side.value, side);
        }
    }

    public static Side valueOf(int side) {
        return map.get(side);
    }

    public int getValue() {
        return value;
    }
}
