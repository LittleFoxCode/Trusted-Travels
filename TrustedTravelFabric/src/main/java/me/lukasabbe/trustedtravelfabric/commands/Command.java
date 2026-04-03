package me.lukasabbe.trustedtravelfabric.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;

public interface Command {
    LiteralArgumentBuilder<CommandSourceStack> createCommand();
    int runCommand(CommandContext<CommandSourceStack> ctx);
}
