package com.playtika.first.appliances.televisor;

import com.playtika.first.appliances.ElectricAppliance;

import java.awt.*;

public class Televisor extends ElectricAppliance {
    public final float screenSize;
    public final DisplayType displayType;
    public final boolean is3DAvailable;
    public final boolean isSmartTVAvailable;
    public final String brandMark;

    public Televisor(int voltage, float screenSize, DisplayType displayType, boolean is3DAvailable, boolean isSmartTVAvailable, String brandMark) {
        super(voltage);
        this.screenSize = screenSize;
        this.displayType = displayType;
        this.is3DAvailable = is3DAvailable;
        this.isSmartTVAvailable = isSmartTVAvailable;
        this.brandMark = brandMark;
    }

    public Televisor(boolean availableBattery, int voltage, float screenSize, DisplayType displayType, boolean is3DAvailable, boolean isSmartTVAvailable, String brendMark) {
        super(availableBattery, voltage);
        this.screenSize = screenSize;
        this.displayType = displayType;
        this.is3DAvailable = is3DAvailable;
        this.isSmartTVAvailable = isSmartTVAvailable;
        this.brandMark = brendMark;
    }

    public Televisor(boolean availableBattery, int voltage, Color color, float screenSize, DisplayType displayType, boolean is3DAvailable, boolean isSmartTVAvailable, String brendMark) {
        super(availableBattery, voltage, color);
        this.screenSize = screenSize;
        this.displayType = displayType;
        this.is3DAvailable = is3DAvailable;
        this.isSmartTVAvailable = isSmartTVAvailable;
        this.brandMark = brendMark;
    }

    @Override
    public boolean turnOn() {
        boolean turnedOn = super.turnOn();
        printTurnedOnOffMessage("on", turnedOn);
        return turnedOn;
    }

    @Override
    public boolean turnOff() {
        boolean turnedOff = super.turnOff();
        printTurnedOnOffMessage("off", turnedOff);
        return turnedOff;
    }

    private void printTurnedOnOffMessage(String mode, boolean result) {
        String status = "was";
        if (!result) status = "wasn't";
        System.out.println("TV " + status + " turned " + mode);
    }
}
