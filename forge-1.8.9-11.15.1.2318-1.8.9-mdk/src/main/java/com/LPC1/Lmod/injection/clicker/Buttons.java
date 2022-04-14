package com.LPC1.Lmod.injection.clicker;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.MouseEvent;
import org.lwjgl.input.Mouse;

import static com.LPC1.Lmod.injection.clicker.AutoClicker.*;

public class Buttons extends GuiScreen {

    private final int xPos;
    private final int yPos;
    private final int width;
    private final int height;
    private long ClickTimeout;


    private final ResourceLocation ButtonPngOn;
    private final ResourceLocation ButtonPngOff;
    private final BooleanSaver booleanSaver;

    private boolean MouseHold;

    public Buttons(int xPos, int yPos, int width, int height, ResourceLocation resourceLocation, ResourceLocation resourceLocation1, BooleanSaver booleanSaver) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.booleanSaver = booleanSaver;
        this.ButtonPngOn = resourceLocation;
        this.ButtonPngOff = resourceLocation1;
    }

    public void DrawButton(Minecraft mc, int mouseX, int mouseY) {
        OnClick(mouseX, mouseY);
        if (booleanSaver.IsClicked()) {
            mc.renderEngine.bindTexture(ButtonPngOn);
            drawScaledCustomSizeModalRect(xPos, yPos, 0, 0, 171, 73, width, height, 171, 73);
        } else {
            mc.renderEngine.bindTexture(ButtonPngOff);
            drawScaledCustomSizeModalRect(xPos, yPos, 0, 0, 171, 73, width, height, 171, 73);
        }
    }

    private void OnClick(int mouseX, int mouseY) {

        if (Mouse.isButtonDown(0)) {
            if (!Mouse.getEventButtonState()) {
                MouseHold = true;
            }
        } else if (MouseHold) {
            MouseHold = false;
        }
        if (mouseX >= xPos && mouseY >= yPos && mouseX < xPos + width && mouseY < yPos + height && !booleanSaver.IsClicked() && MouseHold && ClickTimeout < System.currentTimeMillis()) {
            ClickTimeout = System.currentTimeMillis() + 500;
            booleanSaver.SetClicked(true);

        } else if (mouseX >= xPos && mouseY >= yPos && mouseX < xPos + width && mouseY < yPos + height && booleanSaver.IsClicked() && MouseHold && ClickTimeout < System.currentTimeMillis()) {
            ClickTimeout = System.currentTimeMillis() + 500;
            booleanSaver.SetClicked(false);
        }
    }
}

