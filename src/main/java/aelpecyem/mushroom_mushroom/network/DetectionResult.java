package aelpecyem.mushroom_mushroom.network;

import aelpecyem.mushroom_mushroom.block.MushroomUnit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Provides additional context to {@link MushroomUnit}s getting triggered.
 */
public class DetectionResult {
	private final Level level;
	private final BlockPos sourcePos;
	private final BlockState sourceState;
	private final List<Entity> triggeredBy;
	private @Nullable BlockPos triggeredFrom;

	public DetectionResult(Level level, BlockPos sourcePos, BlockState sourceState) {
		this.level = level;
		this.sourcePos = sourcePos;
		this.sourceState = sourceState;
		this.triggeredBy = new ArrayList<>();
		this.triggeredFrom = null;
	}

	public void setTriggeredFrom(@Nullable BlockPos triggeredFrom) {
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

	public Optional<BlockPos> getTriggeredFrom() {
		return Optional.ofNullable(triggeredFrom);
	}

	public void addTriggerEntity(Entity entity) {
		triggeredBy.add(entity);
	}

	public List<Entity> getTriggerEntities() {
		return triggeredBy;
	}
}
