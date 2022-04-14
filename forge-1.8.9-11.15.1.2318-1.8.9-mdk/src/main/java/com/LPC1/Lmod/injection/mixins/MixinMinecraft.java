package com.LPC1.Lmod.injection.mixins;

import com.LPC1.Lmod.injection.setup.Setup;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
@SideOnly(Side.CLIENT)
public class MixinMinecraft {

    @Inject(method = "run", at = @At("org.spongepowered.asm.mixin.injection.points.MethodHead"))
    private void injectMethod(CallbackInfo info) {
        new Setup();
    }
}
