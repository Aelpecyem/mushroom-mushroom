package aelpecyem.mushroom_mushroom.network;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class EntityDetectionResult extends DetectionResult {
	public EntityDetectionResult(Level level, BlockPos sourcePos, BlockState sourceState) {
		super(level, sourcePos, sourceState);
	}

	@Override
	public boolean isValid() {
		return super.isValid() && !getTriggerEntities().isEmpty();
	}
}
