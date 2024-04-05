package aelpecyem.mushroom_mushroom.block.detect;

import aelpecyem.mushroom_mushroom.network.DetectionResult;
import aelpecyem.mushroom_mushroom.registry.MushroomBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

public class EntityDetectorShroomBlockEntity extends DetectorShroomBlockEntity {
	protected static int RANGE = 8;

	public EntityDetectorShroomBlockEntity(BlockPos pos, BlockState state) {
		super(MushroomBlockEntities.ENTITY_DETECTOR, pos, state);
	}

	@Override
	public Optional<DetectionResult> detect() {
		if (level == null) {
			return Optional.empty();
		}
		Vec3 center = getBlockPos().getCenter();
		Entity nearest = level.getNearestEntity(
			LivingEntity.class,
			TargetingConditions.DEFAULT, null, center.x, center.y, center.z,
			AABB.ofSize(center, RANGE, RANGE, RANGE)
		);
		if (nearest != null) {
			DetectionResult result = new DetectionResult(level, getBlockPos(), getBlockState());
			return Optional.of(result);
		}
		return Optional.empty();
	}
}
