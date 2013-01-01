package petrolcraft.generation;

import java.util.Random;

import petrolcraft.Mod_PetrolCraft;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class OnChunkCreation implements IWorldGenerator {

	private WorldGenerator mGenerator;

	public OnChunkCreation() {
		int maxRadius = Mod_PetrolCraft.sConfig.get("Generation", "OilSands_MaxRadius", 7, "Maximum radius of an oil sands patch").getInt();
		int maxDepth = Mod_PetrolCraft.sConfig.get("Generation", "OilSands_MaxDepth", 3, "Maximum depth of an oil sands patch").getInt();
		double chance = Mod_PetrolCraft.sConfig.get("Generation", "OilSands_ChancePerChunk", 10D,
				"Maximum chance (out of 100) that an oil sands appears in a chunk").getDouble(10D);
		mGenerator = new WorldGenerator(maxRadius, maxDepth, chance);
	}

	/**
	 * @see cpw.mods.fml.common.IWorldGenerator#generate(java.util.Random, int,
	 *      int, net.minecraft.world.World,
	 *      net.minecraft.world.chunk.IChunkProvider,
	 *      net.minecraft.world.chunk.IChunkProvider)
	 */
	@Override
	public void generate(Random pRandom, int pChunkX, int pChunkZ, World pWorld, IChunkProvider pChunkGenerator,
			IChunkProvider pChunkProvider) {
		mGenerator.generate(pRandom, pChunkX, pChunkZ, pWorld, pChunkGenerator, pChunkProvider);
	}

}
