package io.github.thatsmusic99.headsplus.nms.v1_8_R1;

import com.mojang.authlib.GameProfile;
import io.github.thatsmusic99.headsplus.nms.NMSManager;
import io.github.thatsmusic99.headsplus.nms.SearchGUI;
import net.minecraft.server.v1_8_R1.EntityPlayer;
import net.minecraft.server.v1_8_R1.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

public class NMSUtil implements NMSManager {
    @Override
    public SearchGUI getSearchGUI(Player p, SearchGUI.AnvilClickEventHandler a) {
        return new SearchGUI(p, a);
    }

    @Override
    public String getSkullOwnerName(SkullMeta m) {
        return m.getOwner();
    }

    @Override
    public ShapelessRecipe getRecipe(ItemStack i, String name) {
        return new ShapelessRecipe(i);
    }

    @Override
    public OfflinePlayer getOfflinePlayer(String name) {
        return Bukkit.getOfflinePlayer(name);
    }

    @Override
    public Player getPlayer(String name) {
        return Bukkit.getPlayer(name);
    }

    @Override
    public GameProfile getGameProfile(ItemStack s) {
        EntityPlayer e = ((CraftPlayer) Bukkit.getPlayer(((SkullMeta) s.getItemMeta()).getOwner())).getHandle();
        return e.getProfile();
    }

    @Override
    public ItemStack getItemInHand(Player p) {
        return p.getItemInHand();
    }

    public ItemStack getOffHand(Player p) {
        return null;
    }

    @Override
    public ItemStack setCalendarValue(ItemStack i, String value) {
        net.minecraft.server.v1_8_R1.ItemStack is = CraftItemStack.asNMSCopy(i);
        if (is == null) return i;
        if (is.getTag() == null) {
            is.setTag(new NBTTagCompound());
        }
        is.getTag().setString("advent-value", value);
        return CraftItemStack.asBukkitCopy(is);
    }

    @Override
    public ItemStack setOpen(ItemStack i, boolean value) {
        net.minecraft.server.v1_8_R1.ItemStack is = CraftItemStack.asNMSCopy(i);
        if (is.getTag() == null) {
            is.setTag(new NBTTagCompound());
        }
        is.getTag().setBoolean("advent-open", value);
        return CraftItemStack.asBukkitCopy(is);
    }

    @Override
    public boolean isOpen(ItemStack is) {
        net.minecraft.server.v1_8_R1.ItemStack i = CraftItemStack.asNMSCopy(is);
        if (i.getTag() != null) {
            return Objects.requireNonNull(i.getTag()).getBoolean("advent-open");
        }
        return false;
    }

    @Override
    public HashMap<String, String> getNBTTags(ItemStack item) {
        net.minecraft.server.v1_8_R1.ItemStack i = CraftItemStack.asNMSCopy(item);
        HashMap<String, String> keys = new HashMap<>();
        if (i.getTag() != null) {
            for (String str : (Set<String>) i.getTag().c()) {
                keys.put(str, i.getTag().get(str).toString());
            }
        }
        return keys;
    }

    @Override
    public String getNMSVersion() {
        return "nms";
    }

    @Override
    public Sound getEXPSound() {
        return Sound.valueOf("ORB_PICKUP");
    }
}
