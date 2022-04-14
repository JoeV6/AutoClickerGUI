package com.LPC1.Lmod.injection.clicker;

import net.minecraft.event.ClickEvent;


public class BooleanSaver {

    private boolean clicked = false;

    public boolean IsClicked() {
        return this.clicked;
    }

    public void SetClicked(boolean clicked) {

        this.clicked = clicked;
        if (Gui.ButtonStateClicker.IsClicked()) { AutoClicker.ClickerON = true; }

            else { AutoClicker.ClickerON = false; }

        if (Gui.ButtonStateCPS.IsClicked()) { AutoClicker.CPSCMD = true; }

            else { AutoClicker.CPSCMD = false; }

        System.out.println("Clicked");
    }
}

