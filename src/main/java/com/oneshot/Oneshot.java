package com.oneshot;

import com.oneshot.events.DamageEventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = Oneshot.MODID, name = Oneshot.NAME, version = Oneshot.VERSION, acceptableRemoteVersions = "*")
public class Oneshot
{
    public static final String MODID   = "oneshot";
    public static final String NAME    = "Oneshot control";
    public static final String VERSION = "1.0";

    public static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        MinecraftForge.EVENT_BUS.register(new DamageEventHandler());
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        logger.info("Oneshot Control has been successfully loaded, feel safer now?!");
    }
}
