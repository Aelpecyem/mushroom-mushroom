package aelpecyem.mushroom_mushroom.block.detect;

import aelpecyem.mushroom_mushroom.network.DetectionResult;
import aelpecyem.mushroom_mushroom.network.IDetector;
import aelpecyem.mushroom_mushroom.network.Network;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public abstract class DetectorShroomBlockEntity extends BlockEntity implements IDetector<DetectorShroomBlockEntity> {
	private static final int COOLDOWN = 10;
	private int detectCooldown = 0;

	public DetectorShroomBlockEntity(BlockEntityType<? extends DetectorShroomBlockEntity> type,
									 BlockPos pos, BlockState state) {
		super(type, pos, state);
	}

	@Override
	protected void saveAdditional(CompoundTag nbt) {
		super.saveAdditional(nbt);
		nbt.putInt("Cooldown", detectCooldown);
	}

	@Override
	public void load(CompoundTag nbt) {
		super.load(nbt);
		detectCooldown = nbt.getInt("Cooldown");
	}

	public void tick(Level level, BlockPos pos, BlockState state) {
		if (detectCooldown > 0) {
			detectCooldown--;
			return;
		}
		state.setValue(DetectorShroomBlock.LIT, false);
		Optional<DetectionResult> detection = detect();
		detection.ifPresent(result -> {
			Network network = new Network(level, pos);
			state.setValue(DetectorShroomBlock.LIT, true);
			network.stimulate(result);
		});
		detectCooldown = COOLDOWN;
	}
}
