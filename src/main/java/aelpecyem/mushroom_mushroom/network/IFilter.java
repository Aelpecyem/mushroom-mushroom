package aelpecyem.mushroom_mushroom.network;

import org.jetbrains.annotations.Nullable;

public interface IFilter extends INetworkUnit {

	@Nullable DetectionResult applyFilter(DetectionResult result);

	@Override
	default void connect(INetwork network) {
		network.addFilter(this);
	}
}
