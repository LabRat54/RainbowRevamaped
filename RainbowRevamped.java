package labrat.rainbowrevamped;

import labrat.rainbowrevamped.blocks.RainbowCraftingTable;
import labrat.rainbowrevamped.handlers.GuiHandler;
import labrat.rainbowrevamped.lists.BlockList;
import labrat.rainbowrevamped.lists.ItemList;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("rainbowrevamped")
public class RainbowRevamped {

    public static RainbowRevamped instance;
    public static final String modid = "rainbowrevamped";
    private static final Logger logger = LogManager.getLogger(modid);

    public RainbowRevamped(){
        instance = this;

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegisteries);




        //NetworkInstance

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {

        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.GUIFACTORY,
                () -> (openContainer) -> {
                    GuiHandler.handleGuiRequest(openContainer);
                    return null;
                });

        logger.info("Setup method registered");
    }

    private void clientRegisteries(final FMLClientSetupEvent event) {

        GuiHandler.registerGuiScreens();

        logger.info("Setup method registered");

    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents{

        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event) {
            event.getRegistry().registerAll(
                ItemList.rct_block = new ItemBlock(BlockList.rainbow_crafting_table, new Item.Properties()
                        .group(ItemGroup.BUILDING_BLOCKS))
                        .setRegistryName(BlockList.rainbow_crafting_table.getRegistryName())
            );
            logger.info("Items Registered");
        }

        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {
            event.getRegistry().registerAll(
                    BlockList.rainbow_crafting_table = new RainbowCraftingTable(Block.Properties.create(Material.WOOD)
                            .sound(SoundType.WOOD)
                            .hardnessAndResistance(2.0f, 2.0f)
                            .lightValue(1)
                            ).setRegistryName(location("rainbow_crafting_table"))
            );
            logger.info("Blocks Registered");
        }

       /* ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.GUIFACTORY, () -> {
            return (openContainer) -> {
                ResourceLocation location = openContainer.getId();
                if (location.toString().equals(modid + ":rainbow_crafting_table_gui")) {
                    EntityPlayerSP player = Minecraft.getInstance().player;
                    BlockPos pos = openContainer.getAdditionalData().readBlockPos();
                }
                return null;
            };
        });*/

        private static ResourceLocation location(String name){
            return new ResourceLocation(modid, name);
        }
    }
}
