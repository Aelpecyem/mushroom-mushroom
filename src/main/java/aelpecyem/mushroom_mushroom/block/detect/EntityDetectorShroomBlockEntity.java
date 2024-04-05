package aelpecyem.mushroom_mushroom.block.detect;

import aelpecyem.mushroom_mushroom.network.DetectionResult;
import aelpecyem.mushroom_mushroom.network.EntityDetectionResult;
import aelpecyem.mushroom_mushroom.registry.MushroomBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EntityDetectorShroomBlockEntity extends DetectorShroomBlockEntity {
	protected static int RANGE = 8;
	private static final int MAX_ENTITIES = 32;

	public EntityDetectorShroomBlockEntity(BlockPos pos, BlockState state) {
		super(MushroomBlockEntities.ENTITY_DETECTOR, pos, state);
	}

	@Override
	public Optional<DetectionResult> detect() {
		if (level == null) {
			return Optional.empty();
		}
		Vec3 center = getBlockPos().getCenter();
		List<Entity> triggerEntities = new ArrayList<>();
		level.getEntities(EntityTypeTest.forClass(LivingEntity.class),
			AABB.ofSize(center, RANGE, RANGE, RANGE),
			(e) -> true, triggerEntities, MAX_ENTITIES);
		if (!triggerEntities.isEmpty()) {
			DetectionResult result = new EntityDetectionResult(level, getBlockPos(), getBlockState());
			for (Entity triggerEntity : triggerEntities) {
				result.addTriggerEntity(triggerEntity);
			}
			return Optional.of(result);
		}
		return Optional.empty();
	}
}
