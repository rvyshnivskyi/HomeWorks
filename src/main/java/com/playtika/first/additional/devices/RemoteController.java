package com.playtika.first.additional.devices;

import com.playtika.first.appliances.ElectricAppliance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemoteController {
    private static final Logger LOG = LoggerFactory.getLogger(RemoteController.class);
    private final int buttonsCount;

    public RemoteController(int buttonsCount) {
        this.buttonsCount = buttonsCount;
    }

    public boolean turnApplianceOn(ElectricAppliance appliance) {
        LOG.debug("Try to turn on [{}]", appliance.getClass().getSimpleName());
        boolean operationResult = appliance.turnOn();
        LOG.info("Turn on operation was [{}]", operationResult);
        return operationResult;
    }

    public boolean turnApplianceOff(ElectricAppliance appliance) {
        LOG.debug("Try to turn off [{}]", appliance.getClass().getSimpleName());
        boolean operationResult = appliance.turnOff();
        LOG.info("Turn off operation was [{}]", operationResult);
        return operationResult;
    }
}