package aelpecyem.mushroom_mushroom.mushrooms;

import aelpecyem.mushroom_mushroom.block.effect.RedstoneEmitterShroomBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ToggleRedstoneEffector implements Effector {
	public static final int TICKS_LIT = 20;

	@Override
	public void trigger(Level level, BlockPos pos, BlockState state, DetectionResult context) {
		if (!level.isClientSide()) {
			level.setBlock(pos, state.setValue(RedstoneEmitterShroomBlock.LIT, true), 3);
			level.updateNeighborsAt(pos, state.getBlock());
			// other half handled in RedstoneEmitterShroomBlock
			level.scheduleTick(pos, state.getBlock(), TICKS_LIT);
		}
	}
}
