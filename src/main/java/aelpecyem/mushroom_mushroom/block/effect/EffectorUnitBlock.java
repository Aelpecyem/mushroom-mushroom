package aelpecyem.mushroom_mushroom.block.effect;

import aelpecyem.mushroom_mushroom.block.MushroomUnitBlock;
import aelpecyem.mushroom_mushroom.mushrooms.DetectionResult;
import aelpecyem.mushroom_mushroom.mushrooms.Effector;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class EffectorUnitBlock extends MushroomUnitBlock implements Effector {
	private final Effector effector;

	public EffectorUnitBlock(Effector effector) {
		this.effector = effector;
	}

	@Override
	public void trigger(Level level, BlockPos pos, BlockState state, DetectionResult context) {
		if (isActive(level, pos, state)) {
			effector.trigger(level, pos, state, context);
		}
	}
}
