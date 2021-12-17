package com.project.bime.model.user;

public enum UserStatus {
    OK(1),
    NOT_CONFIRMED(2), PASS_CHANGING(3);
    private final int id;

    UserStatus(int id) {
        this.id = id;
    }
    public static UserStatus getType(Integer id) {
        if (id == null) {
            return null;
        }
        for (UserStatus cusType : UserStatus.values()) {
            if (id.equals(cusType.getValue())) {
                return cusType;
            }
        }
        throw new IllegalArgumentException("No matching type for id " + id);
    }
    public int getValue() {
        return id;
    }
}
