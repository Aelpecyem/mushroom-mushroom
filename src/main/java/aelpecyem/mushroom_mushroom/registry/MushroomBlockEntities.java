package aelpecyem.mushroom_mushroom.registry;

import aelpecyem.mushroom_mushroom.MushroomMushroom;
import aelpecyem.mushroom_mushroom.block.detect.EntityDetectorShroomBlockEntity;
import aelpecyem.mushroom_mushroom.block.detect.RedstoneDetectorShroomBlockEntity;
import aelpecyem.mushroom_mushroom.block.effect.RedstoneEmitterShroomBlockEntity;
import aelpecyem.mushroom_mushroom.block.filter.BlacklistShroomBlockEntity;
import aelpecyem.mushroom_mushroom.block.filter.WhitelistShroomBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.quiltmc.qsl.block.entity.api.QuiltBlockEntityTypeBuilder;

public class MushroomBlockEntities {

	public static final BlockEntityType<RedstoneDetectorShroomBlockEntity> REDSTONE_DETECTOR =
		QuiltBlockEntityTypeBuilder.create(RedstoneDetectorShroomBlockEntity::new, MushroomBlocks.RECEIVER_SHROOM)
			.build();
	public static final BlockEntityType<EntityDetectorShroomBlockEntity> ENTITY_DETECTOR =
		QuiltBlockEntityTypeBuilder.create(EntityDetectorShroomBlockEntity::new, MushroomBlocks.ENTITY_DETECTOR_SHROOM)
			.build();
	public static final BlockEntityType<RedstoneEmitterShroomBlockEntity> REDSTONE_EMITTER =
		QuiltBlockEntityTypeBuilder.create(RedstoneEmitterShroomBlockEntity::new, MushroomBlocks.EMITTER_SHROOM)
			.build();
	public static void init() {
		register(REDSTONE_DETECTOR, "redstone_detector");
		register(REDSTONE_EMITTER, "redstone_emitter");
		register(ENTITY_DETECTOR, "entity_detector");
		register(BLACKLIST, "blacklist");
		register(WHITELIST, "whitelist");
	}	public static final BlockEntityType<BlacklistShroomBlockEntity> BLACKLIST =
		QuiltBlockEntityTypeBuilder.create(BlacklistShroomBlockEntity::new, MushroomBlocks.PLAYER_BLACKLIST_SHROOM)
			.build();

	public static final BlockEntityType<WhitelistShroomBlockEntity> WHITELIST =
		QuiltBlockEntityTypeBuilder.create(WhitelistShroomBlockEntity::new, MushroomBlocks.PLAYER_WHITELIST_SHROOM)
			.build();



	private static void register(BlockEntityType<?> type, String name) {
		Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, MushroomMushroom.id(name), type);
	}
}
