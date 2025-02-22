package aelpecyem.mushroom_mushroom.registry;

import aelpecyem.mushroom_mushroom.MushroomMushroom;
import aelpecyem.mushroom_mushroom.block.detect.DetectorShroomBlock;
import aelpecyem.mushroom_mushroom.block.detect.RedstoneReceiverShroomBlock;
import aelpecyem.mushroom_mushroom.block.effect.RedstoneEmitterShroomBlock;
import aelpecyem.mushroom_mushroom.block.filter.UUIDHoldingShroomBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.quiltmc.qsl.tag.api.QuiltTagKey;
import org.quiltmc.qsl.tag.api.TagType;

public class MushroomBlocks {
	public static final TagKey<Block> MUSHROOM_NETWORK_BLOCKS = QuiltTagKey.of(
		BuiltInRegistries.BLOCK.key(),
		MushroomMushroom.id("mushroom_network_blocks"),
		TagType.NORMAL
	);
	public static final Block EMITTER_SHROOM = new RedstoneEmitterShroomBlock();
	public static final Block RECEIVER_SHROOM = new RedstoneReceiverShroomBlock();
	public static final Block ENTITY_DETECTOR_SHROOM =
		new DetectorShroomBlock(() -> MushroomBlockEntities.ENTITY_DETECTOR);
	public static final Block PLAYER_WHITELIST_SHROOM =
		new UUIDHoldingShroomBlock(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM), () -> MushroomBlockEntities.WHITELIST);
	public static final Block PLAYER_BLACKLIST_SHROOM =
		new UUIDHoldingShroomBlock(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM), () -> MushroomBlockEntities.BLACKLIST);

	public static void init() {
		register(EMITTER_SHROOM, "emitter_shroom");

		register(RECEIVER_SHROOM, "receiver_shroom");
		register(ENTITY_DETECTOR_SHROOM, "entity_detector_shroom");

		register(PLAYER_WHITELIST_SHROOM, "whitelist_shroom");
		register(PLAYER_BLACKLIST_SHROOM, "blacklist_shroom");
	}

	private static void register(Block block, String name) {
		Registry.register(BuiltInRegistries.BLOCK, MushroomMushroom.id(name), block);
		Registry.register(BuiltInRegistries.ITEM, MushroomMushroom.id(name),
			new BlockItem(block, new Item.Properties()));
	}
}
