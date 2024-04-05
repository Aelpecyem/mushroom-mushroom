package aelpecyem.mushroom_mushroom;

import aelpecyem.mushroom_mushroom.registry.MushroomBlockEntities;
import aelpecyem.mushroom_mushroom.registry.MushroomBlocks;
import aelpecyem.mushroom_mushroom.registry.MushroomItems;
import net.minecraft.resources.ResourceLocation;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MushroomMushroom implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("Mushroom Mushroom");

	public static ResourceLocation id(String name) {
		return new ResourceLocation("mushroom_mushroom", name);
	}

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("Hello Quilt world from {}!", mod.metadata().name());

		MushroomItems.init();
		MushroomBlockEntities.init();
		MushroomBlocks.init();
	}
}
