package me.lukasabbe.trustedtravelfabric.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;

import java.util.List;

public class Commands {
    public static List<Command> commands = List.of(new ServerCommand());
    public static void createCommands(CommandDispatcher<CommandSourceStack> serverCommandSourceCommandDispatcher, CommandBuildContext commandRegistryAccess, net.minecraft.commands.Commands.CommandSelection registrationEnvironment) {
        commands.forEach(command -> serverCommandSourceCommandDispatcher.register(command.createCommand()));
    }
}
