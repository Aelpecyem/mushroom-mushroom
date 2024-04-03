package aelpecyem.mushroom_mushroom.block;

import aelpecyem.mushroom_mushroom.registry.MushroomBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public abstract class MushroomUnitBlock extends BaseEntityBlock implements MushroomUnit {
	public static final BooleanProperty ENABLED = BlockStateProperties.ENABLED;
	public static final DirectionProperty FACING = BlockStateProperties.FACING;
	protected static final VoxelShape[] SHAPE = {
		Block.box(5.0, 6.0, 5.0, 11.0, 16.0, 11.0), // UP
		Block.box(5.0, 0.0, 5.0, 11.0, 10.0, 11.0), // DOWN
		Block.box(5.0, 5.0, 6.0, 11.0, 11.0, 16.0), // SOUTH
		Block.box(5.0, 5.0, 0.0, 11.0, 11.0, 10.0), // NORTH
		Block.box(6.0, 5.0, 5.0, 16.0, 11.0, 11.0), // EAST
		Block.box(0.0, 5.0, 5.0, 10.0, 11.0, 11.0) // WEST
	};

	public MushroomUnitBlock() {
		super(Properties.copy(Blocks.RED_MUSHROOM)
			.lightLevel(Blocks.litBlockEmission(5)));
		registerDefaultState(defaultBlockState().setValue(ENABLED, true).setValue(FACING, Direction.UP));
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		Vec3 vec3 = state.getOffset(world, pos);
		return SHAPE[state.getValue(FACING).ordinal()].move(vec3.x, vec3.y, vec3.z);
	}


	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
		if (mayPlaceOn(ctx.getLevel().getBlockState(ctx.getClickedPos()
			.relative(ctx.getClickedFace().getOpposite())))) {
			return defaultBlockState().setValue(FACING, ctx.getClickedFace());
		}
		return null;
	}

	private boolean mayPlaceOn(BlockState floor) {
		return floor.is(BlockTags.DIRT) || floor.is(MushroomBlocks.MUSHROOM_NETWORK_BLOCKS);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world,
								  BlockPos pos, BlockPos neighborPos) {
		return !state.canSurvive(world, pos)
			? Blocks.AIR.defaultBlockState()
			: super.updateShape(state, direction, neighborState, world, pos, neighborPos);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
		BlockPos attachedTo = pos.relative(state.getValue(FACING).getOpposite());
		return this.mayPlaceOn(world.getBlockState(attachedTo));
	}

	@Override
	public boolean isActive(Level level, BlockPos pos, BlockState state) {
		return state.getValue(ENABLED);
	}

	@Override
	public void setActive(Level level, BlockPos pos, BlockState state, boolean active) {
		level.setBlock(pos, state.setValue(ENABLED, active), 2);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(ENABLED, FACING);
	}

	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return null;
	}
}
