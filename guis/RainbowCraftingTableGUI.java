package labrat.rainbowrevamped.guis;

import labrat.rainbowrevamped.containers.ContainerRainbowCraftingTable;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RainbowCraftingTableGUI extends GuiContainer {

    private static final ResourceLocation RAINBOW_CRAFTING_TABLE_GUI_TEXTURES = new ResourceLocation("rainbowrevamped:textures/gui/container/rainbow_crafting_table.png");

    public RainbowCraftingTableGUI(InventoryPlayer invPlayer, World world, BlockPos blockPos){
        super(new ContainerRainbowCraftingTable(invPlayer, world, blockPos));

        this.xSize = 88;
        this.ySize = 103;
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fontRenderer.drawString(I18n.format("container.rainbow_crafting_table"), 10F, 192F, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(RAINBOW_CRAFTING_TABLE_GUI_TEXTURES);
        int i = this.guiLeft;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
    }
}
