package aelpecyem.mushroom_mushroom.mushrooms;

import aelpecyem.mushroom_mushroom.block.MushroomUnit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * Provides additional context to {@link MushroomUnit}s getting triggered.
 */
public class DetectionResult {
	private final Level level;
	private final BlockPos sourcePos;
	private final BlockState sourceState;
	private final @Nullable Entity triggeredBy;
	private final @Nullable BlockPos triggeredFrom;

	public DetectionResult(Level level, BlockPos sourcePos, BlockState sourceState,
						   @Nullable Entity triggeredBy, @Nullable BlockPos triggeredFrom) {
		this.level = level;
		this.sourcePos = sourcePos;
		this.sourceState = sourceState;
		this.triggeredBy = triggeredBy;
		this.triggeredFrom = triggeredFrom;
	}

	public Level getLevel() {
		return level;
	}

	public BlockPos getSourcePos() {
		return sourcePos;
	}

	public BlockState getSourceState() {
		return sourceState;
	}

	public Optional<Entity> getTriggeredBy() {
		return Optional.ofNullable(triggeredBy);
	}

	public Optional<BlockPos> getTriggeredFrom() {
		return Optional.ofNullable(triggeredFrom);
	}
}
