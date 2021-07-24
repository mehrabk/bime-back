package com.project.bime.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum BimeType {
    UN_KNOWN(0), BIME_SALES(1), BIME_BADANE(2);

    private final int id;

    BimeType(int id) {
        this.id = id;
    }

    public int getValue() {
        return id;
    }

    public String getLabel() {
        return toString();
    }

    public static BimeType getType(Integer id) {
        System.out.println(id);
        if (id == null) {
            return null;
        }
        for (BimeType item : BimeType.values()) {
            if (id.equals(item.getValue())) {
                return item;
            }
        }
        throw new IllegalArgumentException("No matching type for id " + id);
    }

    @Override
    public String toString() {
        switch (this) {
            case BIME_SALES:
                return "بیمه ثالث";
            case BIME_BADANE:
                return "بیمه بدنه";
            case UN_KNOWN:
                return "";
            default:
                return "ERR";
        }
    }
}
