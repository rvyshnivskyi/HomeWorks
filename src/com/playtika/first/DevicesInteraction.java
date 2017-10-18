package com.playtika.first;

import com.playtika.first.additional.devices.RemoteController;
import com.playtika.first.appliances.AudioSystem;
import com.playtika.first.appliances.televisor.DisplayType;
import com.playtika.first.appliances.televisor.Televisor;

public class DevicesInteraction {
    public static void main(String[] args) {
        Televisor tv = new Televisor(220, 21.1f, DisplayType.LCD, true, false, "Samsung");
        AudioSystem audioSystem = new AudioSystem(220, true, false);
        RemoteController remoteController = new RemoteController(25);
        remoteController.turnApplianceOn(tv);
        remoteController.turnApplianceOn(audioSystem);
        remoteController.turnApplianceOff(tv);
        remoteController.turnApplianceOff(audioSystem);

    }
}

