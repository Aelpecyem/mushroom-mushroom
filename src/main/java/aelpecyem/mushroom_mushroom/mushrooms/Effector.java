package aelpecyem.mushroom_mushroom.mushrooms;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface Effector {
	void trigger(Level level, BlockPos pos, BlockState state, DetectionResult context);
}
