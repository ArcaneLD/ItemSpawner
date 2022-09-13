package fr.arcane.ItemSpawners;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CreditsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("credit")) {
            sender.sendMessage("§l§3[§6ItemSpawner§3]§r §eCredits : Arcane pour le developpement du plugin et Alextogy pour l'idée du plugin");
        }
        return false;
    }

}
