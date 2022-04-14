package com.LPC1.Lmod.injection.clicker;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.event.ClickEvent;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.spongepowered.asm.mixin.Shadow;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.ArrayList;

import static com.LPC1.Lmod.injection.clicker.Gui.mc;
import static com.LPC1.Lmod.injection.clicker.InGameCommands.GenerateSequence;


public class AutoClicker {

    Minecraft minecraft = Minecraft.getMinecraft();

    public static boolean ClickerON = false;
    public static boolean ListGenerated = false;
    public static boolean FirstList = true;
    public static boolean CPS = false;
    public static boolean CPSCMD = false;
    public boolean GuiOpened = false;
    public boolean TickClick = false;

    public static int Ticks = 0;
    private static int Seconds = 0;

    public static int ClickCount = 0;
    public static int TEMP = 0;
    public static int MAXSpeed = 0;
    public static int MINSpeed = 0;
    public static int ReachValue = 0;

    public static ArrayList<Integer> ClickList = new ArrayList<Integer>();




    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {

        ClickerON = false;
        ListGenerated = false;
        FirstList = true;
        CPS = false;
        CPSCMD = false;

    }
    @SubscribeEvent
    public void OnMouseEvent(MouseEvent event) {


        if (event.button == 0 && !TickClick) {
            TickClick = true;
            System.out.println("Click click");
        }
    }
    @SubscribeEvent
    public void OnClientTickEvent(TickEvent.ClientTickEvent event) {

        TickClick = false;

        if (Ticks == 20) {
            Ticks = 0;
            Seconds++;
            System.out.println(Seconds);
        }

        if (Seconds == 1) {
            Seconds = 0;
            if (AutoClicker.ClickerON || Gui.ButtonStateClicker.IsClicked()) {
                GenerateSequence(AutoClicker.MAXSpeed, AutoClicker.MINSpeed, 20);
            }
        }

        if (ClickerON && ListGenerated && ClickList.get(Ticks) == 1) { MouseClick(); }

        AutoPot();

        Ticks++;

        if (event.phase == TickEvent.Phase.END) {

            if (Keyboard.isKeyDown(Keyboard.KEY_O) && !GuiOpened && !Minecraft.getMinecraft().ingameGUI.getChatGUI().getChatOpen() ) {
                mc.displayGuiScreen(new Gui());
                GuiOpened = true;
                System.out.println("GuiOpened");

            } else {
                GuiOpened = false;
            }
        }
    }




    private void MouseClick() {

        if (minecraft.gameSettings.keyBindAttack.isKeyDown()) {
            minecraft.thePlayer.swingItem();

            MinecraftForge.EVENT_BUS.post(new MouseEvent());
            CPS = true;

            switch (minecraft.objectMouseOver.typeOfHit) {
                case ENTITY:
                    minecraft.playerController.attackEntity(minecraft.thePlayer, minecraft.objectMouseOver.entityHit);
                    break;
                case BLOCK:
                    minecraft.playerController.clickBlock(minecraft.objectMouseOver.getBlockPos(), minecraft.objectMouseOver.sideHit);
                    break;
            }
        } else { CPS = false; }
    }

    private void AutoPot() {

        if (minecraft.thePlayer != null && minecraft.theWorld != null) {

            if (minecraft.thePlayer.getHealth() < 10) {

                System.out.println("Low HP Detected");
                minecraft.gameSettings.getKeyBinding(GameSettings.Options);

            }
        }
    }
    public static void MouseClickBot() throws AWTException {
        Robot bot = new Robot();
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
}
