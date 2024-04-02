package aelpecyem.mushroom_mushroom.mushrooms;

import aelpecyem.mushroom_mushroom.block.detect.DetectorShroomBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

public class EntityDetector implements Detector<DetectorShroomBlockEntity> {
	private static final int RANGE = 8;

	@Override
	public Optional<DetectionResult> detect(Level level, BlockPos pos, BlockState state,
											DetectorShroomBlockEntity shroom) {
		Vec3 center = pos.getCenter();
		Entity nearest = level.getNearestEntity(
			LivingEntity.class,
			TargetingConditions.DEFAULT, null, center.x, center.y, center.z,
			AABB.ofSize(center, RANGE, RANGE, RANGE)
		);
		if (nearest != null) {
			DetectionResult result = new DetectionResult(level, pos, state, nearest, nearest.getOnPos());
			return Optional.of(result);
		}
		return Optional.empty();
	}
}
