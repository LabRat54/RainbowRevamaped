package labrat.rainbowrevamped.handlers;

import labrat.rainbowrevamped.guis.RainbowCraftingTableGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.FMLPlayMessages;
import org.apache.logging.log4j.LogManager;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class GuiHandler {

    private static final Map<ResourceLocation, Supplier<BiFunction<InventoryPlayer, BlockPos, GuiScreen>>> GUISUPPLIERS = new HashMap<>();

    public static void registerGuiScreens()
    {
        registerGui("rainbow_crafting_table_gui", () -> (playerInv, pos) -> new RainbowCraftingTableGUI(playerInv, playerInv.player.world, pos));
    }

    private static void registerGui(String id, Supplier<BiFunction<InventoryPlayer, BlockPos, GuiScreen>> returnSupplier)
    {
        ResourceLocation resourceId = new ResourceLocation("rainbowrevamped", id);

        if (!GUISUPPLIERS.containsKey(resourceId))
        {
            GUISUPPLIERS.put(resourceId, returnSupplier);
        }
        else
        {
            LogManager.getLogger().error("Tried to register GuiScreen Supplier multiple times.");
        }
    }

    public static GuiScreen handleGuiRequest(FMLPlayMessages.OpenContainer openContainer)
    {
        ResourceLocation id = openContainer.getId();

        if (GUISUPPLIERS.containsKey(id))
        {
            Supplier<BiFunction<InventoryPlayer, BlockPos, GuiScreen>> returnSupplier = GUISUPPLIERS.get(id);
            EntityPlayerSP player = Minecraft.getInstance().player;
            BlockPos pos = openContainer.getAdditionalData().readBlockPos();

            if (returnSupplier != null && returnSupplier.get() != null)
            {
                return returnSupplier.get().apply(player.inventory, pos);
            }
        }

        return null;
    }
}
