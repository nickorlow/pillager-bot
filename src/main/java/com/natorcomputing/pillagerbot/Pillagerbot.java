package com.natorcomputing.pillagerbot;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;

public final class Pillagerbot extends JavaPlugin implements Listener {


    JDA bot;
    String token = "DISCORD_BOT_KEY";

    @Override
    public void onEnable() {

        try {
            bot = JDABuilder
                    .createDefault(token)
                    .setActivity(Activity.listening("for you to stop breathing"))
                    .build();
            bot.awaitReady();
            Bukkit.getServer().getPluginManager().registerEvents(this, this);
            MessageAction act =  bot.getGuildById(780935259239350353L).getTextChannelById(794609077861744660L).sendMessage("Pillager Bot Initialized!");
            act.complete();
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }
    }



    @EventHandler
    public void OnHealthChange(PlayerDeathEvent e)
    {
        Player p = e.getEntity().getPlayer();
        Location l = p.getLocation();
        bot.getGuildById(780935259239350353L).getTextChannelById(794609077861744660L).sendMessage(p.getName()+" just died at "+Math.round(l.getX())+" "+Math.round(l.getY())+" "+Math.round(l.getZ())+" (XYZ)").complete();
    }



    @Override
    public void onDisable() {
    }
}
