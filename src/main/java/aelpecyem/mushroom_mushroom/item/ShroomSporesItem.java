package aelpecyem.mushroom_mushroom.item;

import aelpecyem.mushroom_mushroom.block.filter.UUIDHolder;
import aelpecyem.mushroom_mushroom.registry.MushroomItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.UUID;

public class ShroomSporesItem extends Item {
	public ShroomSporesItem(Properties settings) {
		super(settings);
	}

	public static ItemStack forUUIDHolder(Player owner, Level level, BlockPos pos) {
		ItemStack stack = new ItemStack(MushroomItems.SHROOM_SPORES);
		CompoundTag tag = new CompoundTag();
		tag.putUUID("Owner", owner.getUUID());
		tag.putString("Level", level.dimension().location().toString());
		tag.put("Pos", NbtUtils.writeBlockPos(pos));
		stack.setTag(tag);
		return stack;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user) {
		CompoundTag tag = stack.getTag();
		if (!world.isClientSide() && tag != null) {
			UUID owner = stack.getTag().getUUID("Owner");
			ResourceLocation target = new ResourceLocation(tag.getString("Level"));
			BlockPos targetPos = NbtUtils.readBlockPos(tag.getCompound("Pos"));

			if (!world.dimension().location().equals(target)) {
				return super.finishUsingItem(stack, world, user);
			}
			ServerLevel targetDim = world.getServer().getLevel(ResourceKey.create(Registries.DIMENSION, target));
			if (targetDim == null) {
				return super.finishUsingItem(stack, world, user);
			}
			BlockEntity targetEntity = targetDim.getBlockEntity(targetPos);
			if (targetEntity instanceof UUIDHolder holder && holder.isOwner(owner)) {
				// todo play pling
				holder.addUUID(user.getUUID());
			}
		}
		return super.finishUsingItem(stack, world, user);
	}
}
