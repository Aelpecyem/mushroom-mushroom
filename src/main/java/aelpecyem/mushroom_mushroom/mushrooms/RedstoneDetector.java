package aelpecyem.mushroom_mushroom.mushrooms;

import aelpecyem.mushroom_mushroom.block.detect.DetectorShroomBlock;
import aelpecyem.mushroom_mushroom.block.detect.DetectorShroomBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public class RedstoneDetector implements Detector<DetectorShroomBlockEntity> {
	@Override
	public Optional<DetectionResult> detect(Level level, BlockPos pos, BlockState state,
											DetectorShroomBlockEntity shroom) {
		if (level.hasNeighborSignal(pos) && !state.getValue(DetectorShroomBlock.LIT)) {
			DetectionResult result = new DetectionResult(level, pos, state, null, null);
			return Optional.of(result);
		}
		return Optional.empty();
	}
}
