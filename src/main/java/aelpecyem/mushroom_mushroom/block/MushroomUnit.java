package aelpecyem.mushroom_mushroom.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

/**
 * <p>
 * Mushroom Units describe any placed mushroom block in a network. <br>
 * Use this interface to interact with them.
 * </p>
 * <p>
 * A Mushroom Unit is a block in a network.
 * When <b>active</b> (see {@link MushroomUnit#isActive(Level, BlockPos, BlockState)} ()}) they may be
 * <b>triggered</b>, causing some kind of effect. <br>
 * Mushroom Units always form a network with their neighbors.
 * Use this interface to communicate with and keep track of them.
 * </p>
 */
public interface MushroomUnit {
	boolean isActive(Level level, BlockPos pos, BlockState state);

	void setActive(Level level, BlockPos pos, BlockState state, boolean active);
}
