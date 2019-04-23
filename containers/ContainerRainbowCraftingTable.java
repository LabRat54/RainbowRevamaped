package labrat.rainbowrevamped.containers;

import labrat.rainbowrevamped.lists.BlockList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ContainerRainbowCraftingTable extends Container {

    public InventoryCrafting craftMatrix;
    public IInventory craftResult;
    private World worldObj;
    private BlockPos pos;
    private EntityPlayer player;

    public ContainerRainbowCraftingTable(InventoryPlayer invPlayer, World world, BlockPos blockPos){
        craftMatrix = new InventoryCrafting(this, 5, 5);
        craftResult = new InventoryCraftResult();
        worldObj = world;
        pos = blockPos;

        this.addSlot(new SlotCrafting(invPlayer.player, craftMatrix, craftResult, 0, 148, 75));

        for (int i = 0; i < 5; i++) {
            for(int k = 0; k < 5; k++) {
                this.addSlot(new Slot(craftMatrix, k + i * 3, 10 + k * 18, 17 + i * 18));
                //this.addSlot(new Slot(craftMatrix, k + i * 3, 112, 109));
            }
        }

        for(int i = 0; i < 3; i++){
            for(int k = 0; k < 9; k++) {
                this.addSlot(new Slot(invPlayer, k + i * 9 + 9, 8 + k * 18, 83 + i * 18));
            }
        }

        for(int l = 0; l < 9; ++l) {
            this.addSlot(new Slot(invPlayer, l, 8 + l * 18, 181));
        }

        onCraftMatrixChanged(craftMatrix);
    }

    public void onContainerClosed(EntityPlayer playerIn) {
        super.onContainerClosed(playerIn);
        if (!worldObj.isRemote) {
            this.clearContainer(playerIn, worldObj, craftMatrix);
        }
    }

    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index == 0) {
                itemstack1.getItem().onCreated(itemstack1, worldObj, playerIn);
                if (!this.mergeItemStack(itemstack1, 10, 46, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (index >= 10 && index < 37) {
                if (!this.mergeItemStack(itemstack1, 37, 46, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= 37 && index < 46) {
                if (!this.mergeItemStack(itemstack1, 10, 37, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 10, 46, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            ItemStack itemstack2 = slot.onTake(playerIn, itemstack1);
            if (index == 0) {
                playerIn.dropItem(itemstack2, false);
            }
        }

        return itemstack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        if (worldObj.getBlockState(pos).getBlock() != BlockList.rainbow_crafting_table) {
            return false;
        } else {
            return playerIn.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }

    public void onCraftMatrixChanged(IInventory inventoryIn) {
        //craftResult.setInventorySlotContents(0, RainbowCraftingTableManager.getinstance().findMatchingRecipe(craftMatrix, worldObj));
        //slotChangedCraftingGrid(worldObj, player, this.craftMatrix, craftResult);
    }
}
