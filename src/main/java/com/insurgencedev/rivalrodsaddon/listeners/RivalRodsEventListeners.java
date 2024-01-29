package com.insurgencedev.rivalrodsaddon.listeners;

import me.rivaldev.fishingrod.rivalfishingrods.api.RodEssenceReceiveEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.insurgencedev.insurgenceboosters.api.IBoosterAPI;
import org.insurgencedev.insurgenceboosters.data.BoosterFindResult;

public final class RivalRodsEventListeners implements Listener {

    @EventHandler
    private void onReceive(RodEssenceReceiveEvent event) {
        final String TYPE = "Essence";
        final String NAMESPACE = "RIVAL_RODS";
        final double[] totalMulti = {0};

        BoosterFindResult pResult = IBoosterAPI.INSTANCE.getCache(event.getPlayer()).getBoosterDataManager().findActiveBooster(TYPE, NAMESPACE);
        if (pResult instanceof BoosterFindResult.Success boosterResult) {
            totalMulti[0] += boosterResult.getBoosterData().getMultiplier();
        }

        IBoosterAPI.INSTANCE.getGlobalBoosterManager().findGlobalBooster(TYPE, NAMESPACE, globalBooster -> {
            totalMulti[0] += globalBooster.getMultiplier();
            return null;
        }, () -> null);

        if (totalMulti[0] > 0) {
            event.setEssence(calculateAmount(event.getEssence(), totalMulti[0]));
        }
    }

    private long calculateAmount(double amount, double multi) {
        return (long) (amount * (multi < 1 ? 1 + multi : multi));
    }
}
