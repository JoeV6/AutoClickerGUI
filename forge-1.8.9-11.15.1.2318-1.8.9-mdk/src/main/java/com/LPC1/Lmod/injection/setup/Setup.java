package com.LPC1.Lmod.injection.setup;

import com.LPC1.Lmod.injection.clicker.AutoClicker;
import com.LPC1.Lmod.injection.clicker.InGameCommands;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = Setup.MODID, version = Setup.VERSION)
public class Setup {

    public static final String MODID = "LPC1";
    public static final String VERSION = "1.0";

    public Setup() {

        ClientCommandHandler.instance.registerCommand(new InGameCommands());
        MinecraftForge.EVENT_BUS.register(new AutoClicker());

    }
}
