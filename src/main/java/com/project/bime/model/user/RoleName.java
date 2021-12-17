package com.project.bime.model.user;

public enum RoleName {
    ROLE_USER(1),
    ROLE_MODERATOR(2),
    ROLE_ADMIN(3);

    private final int id;

    RoleName(int id) {
        this.id = id;
    }
    public static RoleName getType(Integer id) {
        if (id == null) {
            return null;
        }
        for (RoleName cusType: RoleName.values()) {
            if (id.equals(cusType.getValues())) {
                return cusType;
            }
        }
        throw new IllegalArgumentException("No matching type for id" + id);
    }

    public int getValues() {
        return id;
    }
}
