package aelpecyem.mushroom_mushroom.mushrooms;

import aelpecyem.mushroom_mushroom.block.detect.DetectorShroomBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public interface Detector<T extends DetectorShroomBlockEntity> {
	Optional<DetectionResult> detect(Level level, BlockPos pos, BlockState state, T shroom);
}
