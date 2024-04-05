package aelpecyem.mushroom_mushroom.network;

public interface IFilter extends INetworkUnit {

	DetectionResult applyFilter(DetectionResult result);

	@Override
	default void connect(INetwork network) {
		network.addFilter(this);
	}
}
