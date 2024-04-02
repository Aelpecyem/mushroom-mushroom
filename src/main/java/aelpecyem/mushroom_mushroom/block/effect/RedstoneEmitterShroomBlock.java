package aelpecyem.mushroom_mushroom.block.effect;

import aelpecyem.mushroom_mushroom.mushrooms.ToggleRedstoneEffector;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class RedstoneEmitterShroomBlock extends EffectorUnitBlock {
	public static final BooleanProperty LIT = BlockStateProperties.LIT;

	public RedstoneEmitterShroomBlock() {
		super(new ToggleRedstoneEffector());
		registerDefaultState(defaultBlockState().setValue(LIT, false));
	}

	@Override
	public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
		super.tick(state, world, pos, random);
		if (state.getValue(LIT)) {
			world.setBlock(pos, state.cycle(LIT), 2);
			world.updateNeighborsAt(pos, this);
		}
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
