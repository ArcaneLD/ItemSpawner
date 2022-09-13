package fr.arcane.ItemSpawners;



import net.minecraft.core.BlockPosition;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import net.minecraft.nbt.NBTTagCompound;
import  net.minecraft.world.level.block.entity.TileEntityMobSpawner;

import java.util.Objects;

import static org.bukkit.Material.LEGACY_MOB_SPAWNER;


public class ItemSpawnerListener implements Listener {

    @SuppressWarnings(value = "deprecation")
    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event){

        Action ac = event.getAction();

        if (ac == Action.RIGHT_CLICK_BLOCK) {
            Player p = event.getPlayer();
            Block b = event.getClickedBlock();
            Location loc;
            loc = Objects.requireNonNull(b).getLocation();

            if (b.getType().equals(LEGACY_MOB_SPAWNER)) {


                //ArmorStand itemp = (ArmorStand) loc.getWorld().spawnEntity(ploc, EntityType.ARMOR_STAND);
                //itemp.setBasePlate(false);
                //itemp.setVisible(false);
                //itemp.setArms(true);
                //itemp.setRightArmPose(new EulerAngle(265f,270f,0f));
                //itemp.setItemInHand(i);




                ItemStack i = p.getItemInUse();



                CreatureSpawner cs =(CreatureSpawner) b.getState();

                cs.setSpawnedType(EntityType.DROPPED_ITEM);
                cs.update();
                BlockPosition blockPos = new BlockPosition(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
                TileEntityMobSpawner spawner = (TileEntityMobSpawner) ((CraftWorld) Objects.requireNonNull(loc.getWorld())).getHandle().getTileEntity(blockPos);
                NBTTagCompound nbtTileEntity = new NBTTagCompound();

                Objects.requireNonNull(spawner).save(nbtTileEntity);
                nbtTileEntity.remove("SpawnPotentials");

                NBTTagCompound itemstack = new NBTTagCompound();
                NBTTagCompound itemStackTag = new NBTTagCompound();
                net.minecraft.world.item.ItemStack itemStack = CraftItemStack.asNMSCopy(i);
                itemStack.save(itemStackTag);
                itemstack.setString("id", "item");
                itemstack.set("Item", itemStackTag);

                nbtTileEntity.setShort("SpawnCount",(short)3);
                nbtTileEntity.setShort("Delay",(short) 400);
                nbtTileEntity.setShort("MaxSpawnDelay", (short) 200);
                nbtTileEntity.setShort("MinSpawnDelay", (short) 600);
                nbtTileEntity.setShort("SpawnRange", (short) 8);
                nbtTileEntity.setShort("MaxNearbyEntities", (short) 6);
                nbtTileEntity.setShort("RequiredPlayerRange", (short) 30);

                spawner.load(nbtTileEntity);

            }

        }

    }

}

