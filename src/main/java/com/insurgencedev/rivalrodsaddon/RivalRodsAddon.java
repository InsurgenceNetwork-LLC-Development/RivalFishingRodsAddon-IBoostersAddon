package com.insurgencedev.rivalrodsaddon;

import com.insurgencedev.rivalrodsaddon.listeners.RivalRodsEventListeners;
import org.insurgencedev.insurgenceboosters.api.addon.IBoostersAddon;
import org.insurgencedev.insurgenceboosters.api.addon.InsurgenceBoostersAddon;
import org.insurgencedev.insurgenceboosters.libs.fo.Common;

@IBoostersAddon(name = "RivalFishingRodsAddon", version = "1.0.2", author = "InsurgenceDev", description = "RivalFishingRods Support")
public class RivalRodsAddon extends InsurgenceBoostersAddon {

    @Override
    public void onAddonReloadAblesStart() {
        if (Common.doesPluginExist("RivalFishingRods")) {
            registerEvent(new RivalRodsEventListeners());
        }
    }
}
