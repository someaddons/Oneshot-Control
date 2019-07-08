package com.oneshot.events;

import com.oneshot.config.Configurations;
import com.oneshot.Oneshot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DamageEventHandler
{
    /**
     * LivingDamageEvent is fired just before damage is applied to entity.
     * At this point armor, potion and absorption modifiers have already been applied to damage - this is FINAL value.
     * Also note that appropriate resources (like armor durability and absorption extra hearths) have already been consumed.
     *
     * @param event LivingDamageEvent
     */
    @SubscribeEvent
    public void onLivingDamageEvent(LivingDamageEvent event)
    {
        final float eventDamage = event.getAmount();
        float calculatedDmg;

        if (event.getEntityLiving() == null || eventDamage <= 1)
        {
            return;
        }

        // player attacked case
        if (event.getEntityLiving() instanceof EntityPlayer)
        {
            // Environmental damage
            if (event.getSource().getTrueSource() == null)
            {
                calculatedDmg = (float) (Configurations.environmental.maxHPEnvironmentToPlayer < 1.0 ? event.getEntityLiving().getMaxHealth()
                                                                                                         * Configurations.environmental.maxHPEnvironmentToPlayer : eventDamage);
            }
            else
            {
                // Player vs player
                if (event.getSource().getTrueSource() instanceof EntityPlayer)
                {
                    calculatedDmg = (float) (Configurations.entityDamage.maxHPPlayerToPlayer < 1.0
                                               ? event.getEntityLiving().getMaxHealth() * Configurations.entityDamage.maxHPPlayerToPlayer
                                               : eventDamage);
                }
                // Entity vs Player
                else
                {
                    calculatedDmg = (float) (Configurations.entityDamage.maxHPEntityToPlayer < 1.0
                                               ? event.getEntityLiving().getMaxHealth() * Configurations.entityDamage.maxHPEntityToPlayer
                                               : eventDamage);
                }
            }
        }
        // entity attacked case
        else
        {
            // Environmental damage
            if (event.getSource().getTrueSource() == null)
            {
                calculatedDmg = (float) (Configurations.environmental.maxHPEnvironmentToEntity < 1.0 ? event.getEntityLiving().getMaxHealth()
                                                                                                         * Configurations.environmental.maxHPEnvironmentToEntity : eventDamage);
            }
            else
            {
                // Player vs Entity
                if (event.getSource().getTrueSource() instanceof EntityPlayer)
                {
                    calculatedDmg = (float) (Configurations.entityDamage.maxHPPLayerToEntity < 1.0
                                               ? event.getEntityLiving().getMaxHealth() * Configurations.entityDamage.maxHPPLayerToEntity
                                               : eventDamage);
                }
                // Entity vs Entity
                else
                {
                    calculatedDmg = (float) (Configurations.entityDamage.maxHPEntityToEntity < 1.0
                                               ? event.getEntityLiving().getMaxHealth() * Configurations.entityDamage.maxHPEntityToEntity
                                               : eventDamage);
                }
            }
        }
        calculatedDmg = Math.min(eventDamage, calculatedDmg);
        if (calculatedDmg > 0f)
        {
            event.setAmount(Math.max(1f, calculatedDmg));
        }
        else
        {
            event.setAmount(calculatedDmg);
        }

        if (eventDamage != event.getAmount() && Configurations.debug.debugLog)
        {
            if (event.getSource().getTrueSource() != null)
            {
                Oneshot.logger.debug(
                  "Reduced damage from " + event.getSource().getTrueSource().getName() + ", damage:" + eventDamage + " to:" + event.getAmount() + " against target:"
                    + event.getEntityLiving()
                        .getName());
            }
            else
            {
                Oneshot.logger.debug(
                  "Reduced damage from environment, damage:" + eventDamage + " to " + event.getAmount() + " against target:" + event.getEntityLiving());
            }
        }
    }
}
