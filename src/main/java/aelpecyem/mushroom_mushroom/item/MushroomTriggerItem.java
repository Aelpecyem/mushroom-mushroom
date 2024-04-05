package aelpecyem.mushroom_mushroom.item;

import aelpecyem.mushroom_mushroom.network.DetectionResult;
import aelpecyem.mushroom_mushroom.network.IEffector;
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
		if (stateClicked.getBlock() instanceof IEffector m) {
			DetectionResult triggerContext = new DetectionResult(
				context.getLevel(),
				context.getClickedPos(),
				stateClicked
			);
			triggerContext.setTriggeredFrom(context.getClickedPos());
			triggerContext.addTriggerEntity(context.getPlayer());
			m.trigger(triggerContext);
			return InteractionResult.sidedSuccess(true);
		}
		return super.useOn(context);
	}
}
