package labrat.rainbowrevamped.blocks;

import labrat.rainbowrevamped.containers.ContainerRainbowCraftingTable;
import labrat.rainbowrevamped.lists.BlockList;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class RainbowCraftingTable extends Block {

    public RainbowCraftingTable(Block.Properties builder){
        super(builder);

        //builder.hardnessAndResistance(2.0f, 2.0f);
        //builder.lightValue(1);
        //builder.sound(SoundType.WOOD);
    }

    @Override
    public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) {
            return true;
        } else {
            NetworkHooks.openGui((EntityPlayerMP) player, new RainbowCraftingTable.InterfaceRainbowCraftingTable(worldIn, pos), pos);
            player.displayGui(new RainbowCraftingTable.InterfaceRainbowCraftingTable(worldIn, pos));
            return true;
        }

       /* if (!worldIn.isRemote()) {
            player.displayGui(new RainbowCraftingTable.InterfaceRainbowCraftingTable(worldIn, pos));
            //NetworkHooks.openGui((EntityPlayerMP) player, new RainbowCraftingTable.InterfaceRainbowCraftingTable(worldIn, pos), pos);
            //return true;
        }
       return true;*/
    }

    public static class InterfaceRainbowCraftingTable implements IInteractionObject {
        private final World world;
        private final BlockPos position;

        public InterfaceRainbowCraftingTable(World worldIn, BlockPos pos) {
            world = worldIn;
            position = pos;
        }

        public ITextComponent getName() {
            return new TextComponentTranslation(BlockList.rainbow_crafting_table.getTranslationKey() + ".name");
        }

        public boolean hasCustomName() {
            return false;
        }

        @Nullable
        public ITextComponent getCustomName() {
            return null;
        }

        public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
            return new ContainerRainbowCraftingTable(playerInventory, world, position);
        }

        public String getGuiID() {
            return "rainbowrevamped:rainbow_crafting_table_gui";
        }
    }
}
