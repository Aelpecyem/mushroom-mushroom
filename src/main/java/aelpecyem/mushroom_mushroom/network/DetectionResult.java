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
import java.util.function.Predicate;

/**
 * Provides additional context to {@link MushroomUnit}s getting triggered.
 */
public class DetectionResult {
	private final Level level;
	private final BlockPos sourcePos;
	private final BlockState sourceState;
	private @Nullable BlockPos triggeredFrom;
	private final List<Entity> triggeredBy;

	public DetectionResult(Level level, BlockPos sourcePos, BlockState sourceState) {
		this.level = level;
		this.sourcePos = sourcePos;
		this.sourceState = sourceState;
		this.triggeredFrom = null;
		this.triggeredBy = new ArrayList<>();
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


	public DetectionResult removeIf(Predicate<Entity> predicate) {
		this.triggeredBy.removeIf(predicate);
		return this;
	}

	public void addTriggerEntity(Entity entity) {
		triggeredBy.add(entity);
	}

	public List<Entity> getTriggerEntities() {
		return triggeredBy;
	}

	public Optional<BlockPos> getTriggeredFrom() {
		return Optional.ofNullable(triggeredFrom);
	}


	public boolean isValid() {
		return level != null && getSourceState() != null && getSourcePos() != null;
	}
}
