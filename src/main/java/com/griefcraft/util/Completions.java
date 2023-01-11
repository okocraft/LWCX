package com.griefcraft.util;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Completions {

    private static final List<String> LWC = Arrays.asList("create", "modify", "default", "unlock", "info", "limits", "remove", "mode", "flag", "admin");
    private static final List<String> ADMIN = Arrays.asList("view", "find", "forceowner", "remove", "purge", "cleanup", "version", "update", "report", "clear");
    private static final List<String> PROTECTION_TYPES = Arrays.asList("public", "private", "donation", "password", "display", "supply");
    private static final List<String> TOGGLES = Arrays.asList("on", "off");
    private static final List<String> FLAGS = Arrays.asList("redstone", "magnet", "exemption", "autoclose", "allowexplosions", "hopper", "hopperin", "hopperout");
    private static final List<String> DROPTRANSFER = Arrays.asList("select", "on", "off", "status");
    private static final List<String> REMOVE = Arrays.asList("protection", "modes");
    private static final List<String> MODES = Arrays.asList("persist", "nospam", "nolock", "droptransfer");
    private static final List<String> INTEGERS = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");

    private static List<String> modifications = Arrays.asList("-", "@");

    public static List<String> lwc(String term) {
        return filter(LWC, term);
    }

    public static List<String> admin(String term) {
        return filter(ADMIN, term);
    }

    public static List<String> protectionTypes() {
        return PROTECTION_TYPES;
    }

    public static List<String> protectionTypes(String term) {
        return filter(PROTECTION_TYPES, term);
    }

    public static List<String> players(CommandSender requesting) {
        return Bukkit.getOnlinePlayers().stream().filter(p -> !(requesting instanceof Player) || ((Player) requesting).canSee(p)).map(HumanEntity::getName).collect(Collectors.toList());
    }

    public static List<String> players(String term, CommandSender requesting) {
        return filter(players(requesting), term);
    }

    public static List<String> toggles(String term) {
        return filter(TOGGLES, term);
    }

    public static List<String> flags(String term) {
        return filter(FLAGS, term);
    }

    public static List<String> droptransfer(String term) {
        return filter(DROPTRANSFER, term);
    }

    public static List<String> remove(String term) {
        return filter(REMOVE, term);
    }

    public static List<String> modes(String term) {
        return filter(MODES, term);
    }

    public static List<String> cmodify(String term, CommandSender requesting, boolean includeTypes) {
        List<String> players = players(requesting);
        List<String> suggestions = new ArrayList<>(players);
        if (includeTypes) {
            suggestions.addAll(PROTECTION_TYPES);
        }
        if (term.isEmpty()) {
            suggestions.addAll(modifications);
        } else {
            for (String player : players) {
                suggestions.add("-" + player);
                suggestions.add("@" + player);
            }
        }
        return filter(suggestions, term);
    }

    public static List<String> integers(String term) {
        return filter(INTEGERS, term);
    }

    private static List<String> filter(List<String> list, String term) {
        return list.stream().filter(e -> e.toLowerCase().startsWith(term.toLowerCase())).collect(Collectors.toList());
    }

}
