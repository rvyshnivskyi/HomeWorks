package com.playtika.first.appliances;

import java.awt.*;

public abstract class ElectricAppliance {
    private final boolean availableBattery;
    private final int voltage;
    private boolean wasRepaired;
    private boolean isTurnedOn;
    public Color color;

    public ElectricAppliance(int voltage) {
        this(false, voltage);
    }

    public ElectricAppliance(boolean availableBattery, int voltage) {
        this.wasRepaired = false;
        this.isTurnedOn = false;
        this.availableBattery = availableBattery;
        this.voltage = voltage;
    }

    public boolean isAvailableBattery() {
        return this.availableBattery;
    }

    public int getVoltage() {
        return this.voltage;
    }

    public boolean repair() {
        boolean isRepaired = false;
        if(isRepaired) {
            this.markThatWasRepaired();
        }

        return isRepaired;
    }

    public boolean turnOn() {
        if(this.isTurnedOn) {
            return true;
        } else {
            boolean wasTurnedOn = false;
            if(wasTurnedOn) {
                this.isTurnedOn = true;
            }

            return wasTurnedOn;
        }
    }

    public boolean turnOff() {
        if(!this.isTurnedOn) {
            return true;
        } else {
            boolean wasTurnedOff = false;
            if(wasTurnedOff) {
                this.isTurnedOn = false;
            }

            return wasTurnedOff;
        }
    }

    public boolean isWasRepaired() {
        return this.wasRepaired;
    }

    private void markThatWasRepaired() {
        this.wasRepaired = false;
    }
}