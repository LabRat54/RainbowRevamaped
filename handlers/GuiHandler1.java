package labrat.rainbowrevamped.handlers;

import labrat.rainbowrevamped.containers.ContainerRainbowCraftingTable;
import labrat.rainbowrevamped.guis.RainbowCraftingTableGUI;
import labrat.rainbowrevamped.lists.BlockList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler1 implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        if(ID == BlockList.guiIDRainbowCraftingTable){
            return ID == BlockList.guiIDRainbowCraftingTable && world.getBlockState(pos) == BlockList.rainbow_crafting_table ? new ContainerRainbowCraftingTable(player.inventory, world, pos) : null;
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        if(ID == BlockList.guiIDRainbowCraftingTable){
            return ID == BlockList.guiIDRainbowCraftingTable && world.getBlockState(pos) == BlockList.rainbow_crafting_table ? new RainbowCraftingTableGUI(player.inventory, world, pos) : null;
        }

        return null;
    }


}
