package aelpecyem.mushroom_mushroom.block.effect;

import aelpecyem.mushroom_mushroom.network.DetectionResult;
import aelpecyem.mushroom_mushroom.network.IEffector;
import aelpecyem.mushroom_mushroom.registry.MushroomBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class RedstoneEmitterShroomBlockEntity extends BlockEntity implements IEffector {
	private static final int TICKS_LIT = 20;
	private int ticksLeft = 0;

	public RedstoneEmitterShroomBlockEntity(BlockPos pos, BlockState state) {
		super(MushroomBlockEntities.REDSTONE_EMITTER, pos, state);
	}

	@Override
	protected void saveAdditional(CompoundTag nbt) {
		super.saveAdditional(nbt);
		nbt.putInt("TicksLeft", ticksLeft);
	}

	@Override
	public void load(CompoundTag nbt) {
		super.load(nbt);
		ticksLeft = nbt.getInt("TicksLeft");
	}

	public void tick(Level level, BlockPos pos, BlockState state) {
		if (!level.isClientSide() && ticksLeft > 0) {
			ticksLeft--;
			if (ticksLeft == 0) {
				level.setBlock(pos, state.setValue(RedstoneEmitterShroomBlock.LIT, false), 3);
				level.updateNeighborsAt(pos, state.getBlock());
			}
		}
	}

	@Override
	public void trigger(DetectionResult context) {
		// don't emit another redstone if already triggered; continue triggering instead
		if (level != null && !level.isClientSide()) {
			BlockPos pos = getBlockPos();
			BlockState state = getBlockState();
			level.setBlock(pos, state.setValue(RedstoneEmitterShroomBlock.LIT, true), 3);
			level.updateNeighborsAt(pos, state.getBlock());
			ticksLeft = TICKS_LIT;
		}
	}
}
