package aelpecyem.mushroom_mushroom.block.filter;

import java.util.UUID;

public interface UUIDHolder {
	boolean isOwner(UUID uuid);

	boolean isUUIDAccepted(UUID uuid);

	void addUUID(UUID uuid);

	boolean removeUUID(UUID uuid);
}
