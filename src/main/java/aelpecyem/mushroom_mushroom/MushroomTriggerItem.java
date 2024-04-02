package aelpecyem.mushroom_mushroom;

import aelpecyem.mushroom_mushroom.mushrooms.DetectionResult;
import aelpecyem.mushroom_mushroom.mushrooms.Effector;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.state.BlockState;

public class MushroomTriggerItem extends Item {
	public MushroomTriggerItem() {
		super(new Properties().stacksTo(1));
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		BlockState stateClicked = context.getLevel().getBlockState(context.getClickedPos());
		if (stateClicked.getBlock() instanceof Effector m) {
			DetectionResult triggerContext = new DetectionResult(
				context.getLevel(),
				context.getClickedPos(),
				stateClicked,
				context.getPlayer(),
				context.getClickedPos()
			);
			m.trigger(context.getLevel(), context.getClickedPos(), stateClicked, triggerContext);
			return InteractionResult.sidedSuccess(true);
		}
		return super.useOn(context);
	}
}
