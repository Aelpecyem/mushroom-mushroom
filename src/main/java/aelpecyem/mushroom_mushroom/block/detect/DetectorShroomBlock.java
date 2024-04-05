package aelpecyem.mushroom_mushroom.block.detect;

import aelpecyem.mushroom_mushroom.block.ShroomBlock;
import aelpecyem.mushroom_mushroom.network.INetworkUnit;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class DetectorShroomBlock extends ShroomBlock {
	public static final BooleanProperty LIT = BlockStateProperties.LIT;

	public DetectorShroomBlock(Supplier<BlockEntityType<? extends INetworkUnit>> type) {
		super(type);
		registerDefaultState(defaultBlockState().setValue(LIT, false));
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state,
																  BlockEntityType<T> type) {
		return createTickerHelper(type,
			(BlockEntityType<? extends DetectorShroomBlockEntity>) this.type.get(),
			(l, p, s, e) -> e.tick(l, p, s));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(LIT);
	}
}
