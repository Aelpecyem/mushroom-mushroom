package aelpecyem.mushroom_mushroom.block.detect;

import aelpecyem.mushroom_mushroom.mushrooms.DetectionResult;
import aelpecyem.mushroom_mushroom.mushrooms.Effector;
import aelpecyem.mushroom_mushroom.registry.MushroomBlockEntities;
import aelpecyem.mushroom_mushroom.registry.MushroomBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public class DetectorShroomBlockEntity extends BlockEntity {
	private static final int COOLDOWN = 20;
	private static final int MAX_RANGE = 16;
	private int detectCooldown = 0;

	public DetectorShroomBlockEntity(BlockPos pos, BlockState state) {
		super(MushroomBlockEntities.ENTITY_DETECTOR_SHROOM, pos, state);
	}

	public void tick(Level level, BlockPos pos, BlockState state) {
		if (detectCooldown > 0) {
			detectCooldown--;
			return;
		}
		if (state.getBlock() instanceof DetectorShroomBlock d) {
			Optional<DetectionResult> detected = d.getDetector().detect(level, pos, state, this);
			level.setBlock(pos, state.setValue(DetectorShroomBlock.LIT, detected.isPresent()), 2);
			detected.ifPresent(i -> informNetwork(level, pos, state, i));
			detectCooldown = COOLDOWN;
		}
	}

	private void informNetwork(Level level, BlockPos pos, BlockState state, DetectionResult result) {
		BlockPos.breadthFirstTraversal(pos, MAX_RANGE, 128, (posx, consumer) -> {
			if (posx.equals(pos)) {
				consumer.accept(posx.relative(state.getValue(DetectorShroomBlock.FACING).getOpposite()));
			} else {
				for (Direction direction : Direction.values()) {
					consumer.accept(posx.relative(direction));
				}
			}
		}, checkedPos -> {
			if (checkedPos.equals(pos)) {
				return true;
			}
			BlockState checkedState = level.getBlockState(checkedPos);
			if (checkedState.getBlock() instanceof Effector e) {
				e.trigger(level, checkedPos, level.getBlockState(checkedPos), result);
			}
			return level.getBlockState(checkedPos).is(MushroomBlocks.MUSHROOM_NETWORK_BLOCKS);
		});
	}
}
