package mod.linguardium.removeguardianevent.mixin.client;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket.ELDER_GUARDIAN_EFFECT;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientHandlerMixin {
    @Inject(method="onGameStateChange",at=@At(value="INVOKE",target="Lnet/minecraft/network/packet/s2c/play/GameStateChangeS2CPacket;getReason()Lnet/minecraft/network/packet/s2c/play/GameStateChangeS2CPacket$Reason;"), cancellable = true)
    private void ignoreElderGuardianStateChange(GameStateChangeS2CPacket packet, CallbackInfo ci) {
        if ( ELDER_GUARDIAN_EFFECT.equals(packet.getReason())) {
            ci.cancel();
       }
    }
}
