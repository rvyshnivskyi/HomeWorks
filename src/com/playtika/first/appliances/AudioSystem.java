package com.playtika.first.appliances;

public class AudioSystem extends ElectricAppliance {
    public final boolean isCDAvailble;
    public final boolean isFlashAvailble;

    public AudioSystem(int voltage, boolean isCDAvailble, boolean isFlashAvailble) {
        super(voltage);
        this.isCDAvailble = isCDAvailble;
        this.isFlashAvailble = isFlashAvailble;
    }

    public AudioSystem(boolean availableBattery, int voltage, boolean isCDAvailble, boolean isFlashAvailble) {
        super(availableBattery, voltage);
        this.isCDAvailble = isCDAvailble;
        this.isFlashAvailble = isFlashAvailble;
    }
}