package dev.dan.mcutils.item;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

public class TexturedHead extends ItemCreator {

    public TexturedHead(ItemStack i){super(i);}
    public TexturedHead() {
        super(Material.PLAYER_HEAD);
    }

    public TexturedHead(String name) {
        super(Material.PLAYER_HEAD, name);
    }
    public TexturedHead(String name, String base64) {
        super(Material.PLAYER_HEAD, name);
        this.setBase64(base64);
    }
    public TexturedHead(int amount) {
        super(Material.PLAYER_HEAD, amount);
    }

    public TexturedHead(int amount, String name) {
        super(Material.PLAYER_HEAD, amount, name);
    }

    public TexturedHead(Player p){
        super(Material.PLAYER_HEAD);
        ItemStack i = getStack();
        SkullMeta meta = (SkullMeta) i.getItemMeta();
        meta.setOwningPlayer(p);
        i.setItemMeta(meta);
        updateStack(i);
    }
    public TexturedHead(OfflinePlayer p){
        super(Material.PLAYER_HEAD);
        ItemStack i = getStack();
        SkullMeta meta = (SkullMeta) i.getItemMeta();
        meta.setOwningPlayer(p);
        i.setItemMeta(meta);
        updateStack(i);
    }


    public TexturedHead setBase64(String texture) {
        ItemStack head = getStack();
        SkullMeta meta = (SkullMeta) head.getItemMeta();

        PlayerProfile profile = Bukkit.createProfile(UUID.randomUUID());

        profile.setProperty(new ProfileProperty("textures", texture));

        meta.setPlayerProfile(profile);

        head.setItemMeta(meta);
        updateStack(head);
        return this;
    }
}
