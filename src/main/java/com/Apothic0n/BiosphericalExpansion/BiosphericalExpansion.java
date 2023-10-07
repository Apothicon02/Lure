package com.Apothic0n.BiosphericalExpansion;

import com.Apothic0n.BiosphericalExpansion.api.biome.BioxSurfaceRuleData;
import com.Apothic0n.BiosphericalExpansion.api.biome.features.BioxFeatureRegistry;
import com.Apothic0n.BiosphericalExpansion.api.biome.features.decorators.BioxTreeDecoratorType;
import com.Apothic0n.BiosphericalExpansion.api.biome.features.foliage_placers.BioxFoliagePlacerType;
import com.Apothic0n.BiosphericalExpansion.api.biome.features.trunk_placers.BioxTrunkPlacerType;
import com.Apothic0n.BiosphericalExpansion.core.objects.BioxBlocks;
import com.Apothic0n.BiosphericalExpansion.core.objects.BioxItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import terrablender.api.SurfaceRuleManager;

@Mod(BiosphericalExpansion.MODID)
public class BiosphericalExpansion {
    public static final String MODID = "biox";

    public BiosphericalExpansion() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);

        BioxBlocks.BLOCKS.register(eventBus);
        BioxItems.ITEMS.register(eventBus);
        BioxFeatureRegistry.register(eventBus);
        BioxTrunkPlacerType.register(eventBus);
        BioxFoliagePlacerType.register(eventBus);
        BioxTreeDecoratorType.register(eventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            SurfaceRuleManager.setDefaultSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, BioxSurfaceRuleData.makeRules());
            //Regions.register(new GeodeialCavesProvider(new ResourceLocation(MODID, "geodeial_caves_biome_provider"), RegionType.OVERWORLD, 6));
        });
    }

    private void clientSetup(final FMLClientSetupEvent event) {BioxBlocks.fixBlockRenderLayers();}
}