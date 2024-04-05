package aelpecyem.mushroom_mushroom.network;

public interface INetwork {
	void addDetector(IDetector<?> detector);

	void addEffector(IEffector effector);

	void addFilter(IFilter filter);

	void stimulate(DetectionResult detection);
}
