package aelpecyem.mushroom_mushroom.block.detect;

import aelpecyem.mushroom_mushroom.network.DetectionResult;
import aelpecyem.mushroom_mushroom.registry.MushroomBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public class RedstoneDetectorShroomBlockEntity extends DetectorShroomBlockEntity {
	public RedstoneDetectorShroomBlockEntity(BlockPos pos, BlockState state) {
		super(MushroomBlockEntities.REDSTONE_DETECTOR, pos, state);
	}

	@Override
	public Optional<DetectionResult> detect() {
		if (level != null &&
			level.hasNeighborSignal(getBlockPos()) &&
			!getBlockState().getValue(DetectorShroomBlock.LIT)) {
			DetectionResult result = new DetectionResult(level, getBlockPos(), getBlockState());
			return Optional.of(result);
		}
		return Optional.empty();
	}
}
