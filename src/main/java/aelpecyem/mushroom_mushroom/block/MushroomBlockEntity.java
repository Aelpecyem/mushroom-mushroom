package aelpecyem.mushroom_mushroom.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class MushroomBlockEntity extends BlockEntity {

	public MushroomBlockEntity(BlockEntityType<? extends MushroomBlockEntity> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
	}
}
