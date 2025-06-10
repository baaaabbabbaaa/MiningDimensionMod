package com.example.miningdimension;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod(MiningDimensionMod.MODID)
public class MiningDimensionMod {
    public static final String MODID = "miningdimension";

    public MiningDimensionMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
    }

    private void setup(final FMLCommonSetupEvent event) {
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
    }

    @ObjectHolder(MODID + ":portal_block")
    public static Block PORTAL_BLOCK;

    @SubscribeEvent
    public void onBlocksRegistry(final RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> reg = event.getRegistry();
        reg.register(new MiningPortalBlock(Block.Properties.of(net.minecraft.block.material.Material.STONE).strength(3.0F))
                .setRegistryName(new ResourceLocation(MODID, "portal_block")));
    }

    @SubscribeEvent
    public void onItemsRegistry(final RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> reg = event.getRegistry();
        reg.register(new BlockItem(PORTAL_BLOCK, new Item.Properties().tab(ItemGroup.TAB_MISC))
                .setRegistryName(PORTAL_BLOCK.getRegistryName()));
    }
}
