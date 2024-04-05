package aelpecyem.mushroom_mushroom.network;

import aelpecyem.mushroom_mushroom.block.detect.DetectorShroomBlock;
import aelpecyem.mushroom_mushroom.registry.MushroomBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class Network implements INetwork {
	private static final int NETWORK_RANGE = 16;
	private static final int NETWORK_MAX_BLOCKS = 128;
	private final List<IDetector<?>> detectors;
	private final List<IEffector> effectors;
	private final List<IFilter> filters;
	private final Level level;
	private final BlockPos basePos;

	public Network(Level level, BlockPos basePos) {
		this.detectors = new ArrayList<>();
		this.effectors = new ArrayList<>();
		this.filters = new ArrayList<>();
		this.level = level;
		this.basePos = basePos;
		searchNetwork(this);
	}

	private static void searchNetwork(Network network) {
		BlockState startState = network.level.getBlockState(network.basePos);
		BlockPos.breadthFirstTraversal(network.basePos, NETWORK_RANGE, NETWORK_MAX_BLOCKS,
			(posx, consumer) -> {
				if (posx.equals(network.basePos)) {
					// from the start point, go towards the block the shroom is attached to
					consumer.accept(posx.relative(startState.getValue(DetectorShroomBlock.FACING).getOpposite()));
				} else {
					// branch out  otherwise
					for (Direction direction : Direction.values()) {
						consumer.accept(posx.relative(direction));
					}
				}
			},
			checkedPos -> {

				// connect network units to the network
				if (network.level.getBlockEntity(checkedPos) instanceof INetworkUnit e) {
					e.connect(network);
				}

				// traverse over the rest of the network
				if (checkedPos.equals(network.basePos)) {
					return true;
				}
				BlockState checkedState = network.level.getBlockState(checkedPos);
				return checkedState.is(MushroomBlocks.MUSHROOM_NETWORK_BLOCKS);
			});
	}

	@Override
	public void addDetector(IDetector<?> detector) {
		this.detectors.add(detector);
	}

	@Override
	public void addEffector(IEffector effector) {
		this.effectors.add(effector);
	}

	@Override
	public void addFilter(IFilter filter) {
		this.filters.add(filter);
	}

	@Override
	public void stimulate(DetectionResult detection) {
		for (IFilter filter : filters) {
			detection = filter.applyFilter(detection);
			if (detection == null) {
				return;
			}
		}
		// check if the detection is valid so filters can invalidate them beforehand
		if (detection.isValid()) {
			for (IEffector effector : effectors) {
				effector.trigger(detection);
			}
		}
	}
}
