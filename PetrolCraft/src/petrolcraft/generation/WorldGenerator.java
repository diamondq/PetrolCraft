package petrolcraft.generation;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import petrolcraft.blocks.Blocks;

/**
 * This class is responsible for generating any aspect of the world, whether to
 * an existing world or to a new one
 * 
 * @author diamondq
 * 
 */
public class WorldGenerator {

	private int mMaxRadius = 7;
	private boolean mCurrentlyGenerating = false;
	private int mMaxDepth;
	private double mChancePerChunk;

	public WorldGenerator(int pMaxRadius, int pMaxDepth, double pChancePerChunk) {
		mMaxRadius = pMaxRadius;
		mMaxDepth = pMaxDepth;
		mChancePerChunk = pChancePerChunk;
	}

	private void generateOilSand(int pCenterX, int pCenterZ, World pWorld, Random pRandom) {
		int radius = pRandom.nextInt(mMaxRadius - 1) + 1;

		/* Make sure all the chunks around the current point are created */

		int minX = (pCenterX - (radius / 2)) >> 4;
		int minZ = (pCenterZ - (radius / 2)) >> 4;
		int maxX = (pCenterX + (radius / 2)) >> 4;
		int maxZ = (pCenterZ + (radius / 2)) >> 4;

		IChunkProvider cp = pWorld.getChunkProvider();
		for (int var7 = minX; var7 <= maxX; ++var7) {
			for (int var8 = minZ; var8 <= maxZ; ++var8) {
				if (cp.chunkExists(var7, var8) == false) {
					/*
					 * NOTE: This will recursively call the world generator, but
					 * we're protected
					 */
					cp.provideChunk(var7, var8);
				}
			}
		}

		for (int x = 0; x < radius; x++)
			for (int z = 0; z < radius; z++) {
				int testX = pCenterX - (radius / 2) + x;
				int testZ = pCenterZ - (radius / 2) + z;
				int height = pWorld.getHeightValue(testX, testZ) - 1;
				int blockId = pWorld.getBlockId(testX, height, testZ);
				if ((blockId == Block.grass.blockID) || (blockId == Block.dirt.blockID))
					pWorld.setBlock(testX, height, testZ, Blocks.sOilDirt.blockID);
				else if (blockId == Block.sand.blockID)
					pWorld.setBlock(testX, height, testZ, Blocks.sOilSand.blockID);
			}
	}

	public void generate(Random pRandom, int pChunkX, int pChunkZ, World pWorld, IChunkProvider pChunkGenerator,
			IChunkProvider pChunkProvider) {
		if (mCurrentlyGenerating == true)
			return;
		mCurrentlyGenerating = true;
		try {
			/* Check to see if we should generate an oil sand in this chunk */

			if (pRandom.nextDouble() * 100D < mChancePerChunk)
				generateOilSand((pChunkX << 4) + pRandom.nextInt(15), (pChunkZ << 4) + pRandom.nextInt(15), pWorld, pRandom);
		} finally {
			mCurrentlyGenerating = false;
		}
	}
}
