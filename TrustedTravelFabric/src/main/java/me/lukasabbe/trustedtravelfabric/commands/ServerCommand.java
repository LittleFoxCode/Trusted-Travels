package me.lukasabbe.trustedtravelfabric.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import me.lukasabbe.trustedtravelfabric.TrustedTravelFabric;
import me.lukasabbe.trustedtravelfabric.config.ServerObj;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.ClientboundTransferPacket;
import net.minecraft.server.level.ServerPlayer;

import java.util.List;
import java.util.Optional;

public class ServerCommand implements Command {

    @Override
    public LiteralArgumentBuilder<CommandSourceStack> createCommand() {
        return Commands.literal("server")
                .then(Commands
                        .argument("servers", StringArgumentType.word())
                        .suggests(new ServerSuggestionProvider())
                        .executes(this::runCommand));
    }

    @Override
    public int runCommand(CommandContext<CommandSourceStack> ctx) {
        List<ServerObj> servers = TrustedTravelFabric.serverConfig.servers;
        String server = StringArgumentType.getString(ctx,"servers");
        Optional<ServerObj> OptionalServerObj = servers.stream().filter(args -> args.name.equals(server)).findFirst();
        if(OptionalServerObj.isEmpty()){
            ctx.getSource().sendFailure(Component.literal("There is no server with that name"));
            return 0;
        }
        ServerObj serverObj = OptionalServerObj.get();
        ClientboundTransferPacket transferPacket = new ClientboundTransferPacket(serverObj.address, serverObj.port);
        if(!ctx.getSource().isPlayer()) {
            ctx.getSource().sendFailure(Component.literal("Only players can execute this command"));
            return 0;
        }
        ServerPlayer player = ctx.getSource().getPlayer();
        player.connection.send(transferPacket);
        return 1;
    }
}
