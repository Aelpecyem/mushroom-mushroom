package aelpecyem.mushroom_mushroom.block.filter;

import aelpecyem.mushroom_mushroom.network.DetectionResult;
import aelpecyem.mushroom_mushroom.network.IFilter;
import aelpecyem.mushroom_mushroom.registry.MushroomBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class BlacklistShroomBlockEntity extends UUIDHoldingShroomBlockEntity implements IFilter {

	public BlacklistShroomBlockEntity(BlockPos pos, BlockState state) {
		super(MushroomBlockEntities.BLACKLIST, pos, state);
	}

	@Override
	public DetectionResult applyFilter(DetectionResult result) {
		return result.removeIf(e -> isUUIDAccepted(e.getUUID()));
	}
}
