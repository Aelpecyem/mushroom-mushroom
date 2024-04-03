package aelpecyem.mushroom_mushroom.registry;

import aelpecyem.mushroom_mushroom.MushroomMushroom;
import aelpecyem.mushroom_mushroom.MushroomTriggerItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;

public class MushroomItems {
	public static final Item MUSHROOM_TINGLER = new MushroomTriggerItem();

	public static void init() {
		register(MUSHROOM_TINGLER, "mushroom_tingler");
	}

	private static void register(Item item, String name) {
		Registry.register(BuiltInRegistries.ITEM, MushroomMushroom.id(name), item);
	}
}
