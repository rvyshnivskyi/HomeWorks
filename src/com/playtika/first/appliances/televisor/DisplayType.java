package com.playtika.first.appliances.televisor;

public enum DisplayType {
    LED("LED"),
    LCD("LSD"),
    OLED("OLED"),
    CRT("CRT"),
    PLASMA("Plasma");

    private String typeName;

    DisplayType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
