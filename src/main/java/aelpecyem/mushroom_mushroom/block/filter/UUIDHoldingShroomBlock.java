package aelpecyem.mushroom_mushroom.block.filter;

import aelpecyem.mushroom_mushroom.block.ShroomBlock;
import aelpecyem.mushroom_mushroom.network.INetworkUnit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class UUIDHoldingShroomBlock extends ShroomBlock {
	public UUIDHoldingShroomBlock(Properties properties, Supplier<BlockEntityType<? extends INetworkUnit>> type) {
		super(properties, type);
	}

	@Override
	public void setPlacedBy(Level world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
		super.setPlacedBy(world, pos, state, placer, itemStack);
		BlockEntity entity = world.getBlockEntity(pos);
		if (entity instanceof UUIDHoldingShroomBlockEntity u) {
			u.onPlaced(placer);
		}
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		BlockEntity entity = world.getBlockEntity(pos);
		if (!world.isClientSide() && entity instanceof UUIDHoldingShroomBlockEntity u
			&& hand == InteractionHand.MAIN_HAND) {
			return u.createSpores(player);
		}
		return InteractionResult.FAIL;
	}
}
