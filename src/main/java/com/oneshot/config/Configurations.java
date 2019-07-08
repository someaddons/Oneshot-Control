package com.oneshot.config;

import net.minecraftforge.common.config.Config;

import static com.oneshot.Oneshot.MODID;

@Config(modid = MODID)
public class Configurations
{
    @Config.Comment("All configuration related to damage not caused by living entities")
    public static final Environmental environmental = new Environmental();

    public static class Environmental
    {
        @Config.Comment("Configure how much hp a Player looses max from any source not caused by a living entity: default:1.0")
        @Config.RangeDouble(min = 0, max = 1.0)
        public double maxHPEnvironmentToPlayer = 1.0;

        @Config.Comment("Configure how much hp an Entity looses max from any source not caused by a living entity: default:1.0")
        @Config.RangeDouble(min = 0, max = 1.0)
        public double maxHPEnvironmentToEntity = 1.0;
    }

    @Config.Comment("All configuration related to damage caused by living entities: Mobs players etc")
    public static final EntityDamage entityDamage = new EntityDamage();

    public static class EntityDamage
    {
        @Config.Comment("Configure how much hp a Player looses max from another players attack: default:0.35")
        @Config.RangeDouble(min = 0, max = 1.0)
        public double maxHPPlayerToPlayer = 0.35;

        @Config.Comment("Configure how much hp a Player looses max from an entities(not player) attack: default:0.35")
        @Config.RangeDouble(min = 0, max = 1.0)
        public double maxHPEntityToPlayer = 0.35;

        @Config.Comment("Configure how much hp an Entity(not player) looses max from a Player attack: default:0.6")
        @Config.RangeDouble(min = 0, max = 1.0)
        public double maxHPPLayerToEntity = 0.6;

        @Config.Comment("Configure how much hp an Entity(not player) looses max from entities(not player) attacks: default:0.3")
        @Config.RangeDouble(min = 0, max = 1.0)
        public double maxHPEntityToEntity = 0.3;
    }

    @Config.Comment("Debug Settings")
    public static final Debug debug = new Debug();

    public static class Debug
    {
        @Config.Comment("Log changed damage values: default:false")
        public boolean debugLog = false;
    }
}
