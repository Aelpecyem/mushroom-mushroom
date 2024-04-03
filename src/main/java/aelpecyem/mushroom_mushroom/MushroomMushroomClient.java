package aelpecyem.mushroom_mushroom;

import aelpecyem.mushroom_mushroom.registry.MushroomBlocks;
import net.minecraft.client.renderer.RenderType;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.block.extensions.api.client.BlockRenderLayerMap;

@ClientOnly
public class MushroomMushroomClient implements ClientModInitializer {

	@Override
	public void onInitializeClient(ModContainer mod) {
		BlockRenderLayerMap.put(RenderType.cutout(), MushroomBlocks.EMITTER_SHROOM, MushroomBlocks.RECEIVER_SHROOM);
	}
}
