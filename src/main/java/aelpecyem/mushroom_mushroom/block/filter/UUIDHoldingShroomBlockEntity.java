package aelpecyem.mushroom_mushroom.block.filter;

import aelpecyem.mushroom_mushroom.item.ShroomSporesItem;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class UUIDHoldingShroomBlockEntity extends BlockEntity implements UUIDHolder {
	private final Set<UUID> storedUUIDs = new HashSet<>();
	private @Nullable UUID owner;

	public UUIDHoldingShroomBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
	}

	public void onPlaced(@Nullable LivingEntity placer) {
		if (placer != null) {
			owner = placer.getUUID();
		}
	}

	public InteractionResult createSpores(Player user) {
		if (user.getUUID().equals(owner)) {
			user.addItem(ShroomSporesItem.forUUIDHolder(user, getLevel(), getBlockPos()));
			return InteractionResult.sidedSuccess(getLevel().isClientSide());
		}
		return InteractionResult.FAIL;
	}

	@Override
	protected void saveAdditional(CompoundTag nbt) {
		super.saveAdditional(nbt);
		ListTag tagList = new ListTag();
		for (UUID storedUUID : storedUUIDs) {
			tagList.add(NbtUtils.createUUID(storedUUID));
		}
		nbt.put("UUIDs", tagList);
		if (owner != null) {
			nbt.putUUID("Owner", owner);
		}
	}

	@Override
	public void load(CompoundTag nbt) {
		super.load(nbt);
		storedUUIDs.clear();
		ListTag uuidTags = nbt.getList("UUIDs", CompoundTag.TAG_INT_ARRAY);
		storedUUIDs.addAll(uuidTags.stream().map(NbtUtils::loadUUID).collect(Collectors.toSet()));
		if (nbt.contains("Owner")) {
			owner = nbt.getUUID("Owner");
		}
	}

	@Override
	public boolean isOwner(UUID uuid) {
		return uuid.equals(owner);
	}

	@Override
	public boolean isUUIDAccepted(UUID uuid) {
		return storedUUIDs.contains(uuid);
	}

	@Override
	public void addUUID(UUID uuid) {
		setChanged();
		storedUUIDs.add(uuid);
	}

	@Override
	public boolean removeUUID(UUID uuid) {
		setChanged();
		return storedUUIDs.remove(uuid);
	}
}
