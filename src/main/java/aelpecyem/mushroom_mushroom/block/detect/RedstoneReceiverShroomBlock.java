package aelpecyem.mushroom_mushroom.block.detect;

import aelpecyem.mushroom_mushroom.mushrooms.RedstoneDetector;
import net.minecraft.world.level.block.state.BlockState;

public class RedstoneReceiverShroomBlock extends DetectorShroomBlock {

	public RedstoneReceiverShroomBlock() {
		super(new RedstoneDetector());
	}

	@Override
	public boolean isSignalSource(BlockState state) {
		return true;
	}
}
