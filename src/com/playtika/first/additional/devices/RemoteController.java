package com.playtika.first.additional.devices;

import com.playtika.first.appliances.ElectricAppliance;

public class RemoteController {
    private final int buttonsCount;

    public RemoteController(int buttonsCount) {
        this.buttonsCount = buttonsCount;
    }

    public boolean turnApplianceOn(ElectricAppliance appliance) {
        System.out.println("Try to turn on [" + appliance.getClass().getSimpleName() + "]");
        boolean operationResult = appliance.turnOn();
        System.out.println("Turn on operation was [" + operationResult + "]");
        return operationResult;
    }

    public boolean turnApplianceOff(ElectricAppliance appliance) {
        System.out.println("Try to turn off [" + appliance.getClass().getSimpleName() + "]");
        boolean operationResult = appliance.turnOff();
        System.out.println("Turn off operation was [" + operationResult + "]");
        return operationResult;
    }
}
