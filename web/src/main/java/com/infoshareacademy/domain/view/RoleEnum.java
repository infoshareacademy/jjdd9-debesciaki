package com.infoshareacademy.domain.view;

public enum RoleEnum {
    GUEST(1),
    USER(2),
    ADMIN(3),
    SUPER_ADMIN(4);

    private int level;

    RoleEnum(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
