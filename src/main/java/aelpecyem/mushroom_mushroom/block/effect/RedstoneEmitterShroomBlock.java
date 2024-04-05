package aelpecyem.mushroom_mushroom.block.effect;

import aelpecyem.mushroom_mushroom.block.ShroomBlock;
import aelpecyem.mushroom_mushroom.registry.MushroomBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.Nullable;

public class RedstoneEmitterShroomBlock extends ShroomBlock {
	public static final BooleanProperty LIT = BlockStateProperties.LIT;

	public RedstoneEmitterShroomBlock() {
		super(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM).lightLevel(Blocks.litBlockEmission(5)),
			() -> MushroomBlockEntities.REDSTONE_EMITTER);
		registerDefaultState(defaultBlockState().setValue(LIT, false));
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state,
																  BlockEntityType<T> type) {
		return createTickerHelper(type,
			(BlockEntityType<? extends RedstoneEmitterShroomBlockEntity>) this.type.get(),
			(l, p, s, e) -> e.tick(l, p, s));
	}

	@Override
	public boolean isSignalSource(BlockState state) {
		return true;
	}

	@Override
	public int getSignal(BlockState state, BlockGetter world, BlockPos pos, Direction direction) {
		return state.getValue(LIT) ? 15 : 0;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(LIT);
	}
}
