package com.playtika.first.appliances;

import java.awt.*;

public abstract class ElectricAppliance {
    private final boolean availableBattery;
    private final int voltage;
    private boolean wasRepaired = false;
    public boolean isTurnedOn = false;
    public Color color;

    public ElectricAppliance(int voltage) {
        this(false, voltage);
    }

    public ElectricAppliance(boolean availableBattery, int voltage) {
        this.availableBattery = availableBattery;
        this.voltage = voltage;
    }

    public ElectricAppliance(boolean availableBattery, int voltage, Color color) {
        this.availableBattery = availableBattery;
        this.voltage = voltage;
        this.color = color;
    }

    public boolean isAvailableBattery() {
        return availableBattery;
    }

    public int getVoltage() {
        return voltage;
    }

    public boolean repair() {
        boolean isRepaired = false;
//        need implement logic;
        if (isRepaired) changeRepairedStatus();
        return isRepaired;
    }

    public boolean turnOn() {
        if (isTurnedOn) return true;
        else {
            boolean wasTurnedOn = false;
//            need implement logic
            if (wasTurnedOn) this.isTurnedOn = true;
            return wasTurnedOn;
        }
    }

    public boolean turnOff() {
        if (!isTurnedOn) return true;
        else {
            boolean wasTurnedOff = false;
//            need implement logic
            if (wasTurnedOff) this.isTurnedOn = false;
            return wasTurnedOff;
        }
    }

    public boolean isWasRepaired() {
        return wasRepaired;
    }

    private void changeRepairedStatus() {
        this.wasRepaired = false;
    }
}
