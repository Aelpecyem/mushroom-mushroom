package aelpecyem.mushroom_mushroom.registry;

import aelpecyem.mushroom_mushroom.MushroomMushroom;
import aelpecyem.mushroom_mushroom.block.detect.DetectorShroomBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.quiltmc.qsl.block.entity.api.QuiltBlockEntityTypeBuilder;

public class MushroomBlockEntities {
	public static void init() {
		register(ENTITY_DETECTOR_SHROOM, "entity_detector");
	}	public static final BlockEntityType<DetectorShroomBlockEntity> ENTITY_DETECTOR_SHROOM =
		QuiltBlockEntityTypeBuilder.create(DetectorShroomBlockEntity::new,
				MushroomBlocks.RECEIVER_SHROOM,
				MushroomBlocks.ENTITY_DETECTOR_SHROOM)
			.build();

	private static void register(BlockEntityType<?> type, String name) {
		Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, MushroomMushroom.id(name), type);
	}


}
