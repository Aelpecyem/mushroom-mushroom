package aelpecyem.mushroom_mushroom.network;

public interface IEffector extends INetworkUnit {
	void trigger(DetectionResult context);

	@Override
	default void connect(INetwork network) {
		network.addEffector(this);
	}
}
