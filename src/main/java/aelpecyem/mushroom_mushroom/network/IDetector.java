package aelpecyem.mushroom_mushroom.network;

import aelpecyem.mushroom_mushroom.block.detect.DetectorShroomBlockEntity;

import java.util.Optional;

public interface IDetector<T extends DetectorShroomBlockEntity> extends INetworkUnit {
	Optional<DetectionResult> detect();

	@Override
	default void connect(INetwork network) {
		network.addDetector(this);
	}
}
