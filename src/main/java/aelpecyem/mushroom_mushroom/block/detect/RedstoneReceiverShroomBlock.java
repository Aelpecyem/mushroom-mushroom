package aelpecyem.mushroom_mushroom.block.detect;

import aelpecyem.mushroom_mushroom.registry.MushroomBlockEntities;
import net.minecraft.world.level.block.state.BlockState;

public class RedstoneReceiverShroomBlock extends DetectorShroomBlock {

	public RedstoneReceiverShroomBlock() {
		super(() -> MushroomBlockEntities.REDSTONE_DETECTOR);
	}

	@Override
	public boolean isSignalSource(BlockState state) {
		return true;
	}
}
