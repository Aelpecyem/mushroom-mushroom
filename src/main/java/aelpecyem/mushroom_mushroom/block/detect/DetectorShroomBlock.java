package aelpecyem.mushroom_mushroom.block.detect;

import aelpecyem.mushroom_mushroom.block.MushroomUnitBlock;
import aelpecyem.mushroom_mushroom.mushrooms.Detector;
import aelpecyem.mushroom_mushroom.registry.MushroomBlockEntities;
import net.minecraft.core.BlockPos;
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

public class DetectorShroomBlock extends MushroomUnitBlock {
	public static final BooleanProperty LIT = BlockStateProperties.LIT;
	private final Detector detector;

	public DetectorShroomBlock(Detector detector) {
		super();
		this.detector = detector;
		registerDefaultState(defaultBlockState().setValue(LIT, false));
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state,
																  BlockEntityType<T> type) {
		return createTickerHelper(type, MushroomBlockEntities.ENTITY_DETECTOR_SHROOM, (l, p, s, e) -> e.tick(l, p, s));
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new DetectorShroomBlockEntity(pos, state);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(LIT);
	}

	public Detector getDetector() {
		return detector;
	}
}
