package me.lukasabbe.trustedtravelfabric.mixin;

import com.mojang.brigadier.context.CommandContext;
import me.lukasabbe.trustedtravelfabric.TrustedTravelFabric;
import net.minecraft.server.commands.ReloadCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ReloadCommand.class)
public class ReloadCommandMixin {
    @Inject(method = "lambda$register$0", at = @At("HEAD"))
    private static void reloadInsert(CommandContext s, CallbackInfoReturnable<Integer> cir){
        TrustedTravelFabric.serverConfig.reloadConfig();
    }
}
