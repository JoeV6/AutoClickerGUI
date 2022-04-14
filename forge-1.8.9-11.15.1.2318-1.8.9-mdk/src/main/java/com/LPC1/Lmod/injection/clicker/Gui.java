package com.LPC1.Lmod.injection.clicker;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import scala.util.control.TailCalls;

import java.util.ArrayList;
import java.util.List;

public class Gui extends GuiScreen {

    protected List<Buttons> ButtonList = new ArrayList<Buttons>();

    public static final BooleanSaver ButtonStateClicker = new BooleanSaver();
    public static final BooleanSaver ButtonStateCPS = new BooleanSaver();

    private static final ResourceLocation MainGuiPng = new ResourceLocation("GuiMainBackground.png");

    private final ResourceLocation ButtonFalsePng = new ResourceLocation("ButtonFalse.png");
    private final ResourceLocation ButtonTruePng = new ResourceLocation("ButtonTrue.png");


    static Minecraft mc = Minecraft.getMinecraft();

    static final int GuiWidth = (670 / 2);
    static final int GuiHeight = (232 / 2);


    @Override
    public void drawScreen(int mouseX, int mouseY, float PartialTicks) {

        Minecraft mc = Minecraft.getMinecraft();

        drawDefaultBackground();


        int CenterX = (width / 2) - (GuiWidth / 2);
        int CenterY = (height / 2) - (GuiHeight / 2);


        mc.renderEngine.bindTexture(MainGuiPng);
        drawScaledCustomSizeModalRect(CenterX, CenterY, 0, 0, 670, 232, GuiWidth, GuiHeight, 670, 232);

        for (Buttons buttons : this.ButtonList) {
            buttons.DrawButton(mc, mouseX, mouseY);
        }
    }

    @Override
    public void initGui() {
        super.initGui();
        int CenterX = (width / 2) - (GuiWidth / 2);
        int CenterY = (height / 2) - (GuiHeight / 2);
        ButtonList.clear();
        ButtonList.add(new Buttons(CenterX + 244, CenterY + 21, 14, 7, ButtonTruePng, ButtonFalsePng, ButtonStateClicker));
        ButtonList.add(new Buttons(CenterX + 244, CenterY + 69, 14, 7, ButtonTruePng, ButtonFalsePng, ButtonStateCPS));
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}


