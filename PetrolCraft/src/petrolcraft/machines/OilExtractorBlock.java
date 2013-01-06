package petrolcraft.machines;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import petrolcraft.blocks.Blocks;
import petrolcraft.common.Textures;

public class OilExtractorBlock extends Block {
	public OilExtractorBlock(int pBlockID) {
		super(pBlockID, Textures.sOIL_EXTRACTOR_TOP_ID, Material.ground);
		setBlockName("OilExtractor");
		setResistance(0F);
		setHardness(0.5F);
		setCreativeTab(CreativeTabs.tabTools);
		setStepSound(soundMetalFootstep);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	private int view = 0;
	private long lastFlip = System.currentTimeMillis();

	/**
	 * 0 -bottom 1 - top 2 - left 3 - right 4 - front 5 - back
	 * 
	 * @see net.minecraft.block.Block#getBlockTextureFromSide(int)
	 */
	@Override
	public int getBlockTextureFromSide(int pSide) {
		if (System.currentTimeMillis() - lastFlip > 1000) {
			view += 16;
			if (view > 32)
				view = 0;
			lastFlip = System.currentTimeMillis();
		}
		switch (pSide) {
		case 0:
			return Textures.sOIL_EXTRACTOR_BOTTOM_ID + view;
		case 1:
			return Textures.sOIL_EXTRACTOR_TOP_ID + view;
		case 2:
			return Textures.sOIL_EXTRACTOR_LEFT_ID + view;
		case 3:
			return Textures.sOIL_EXTRACTOR_RIGHT_ID + view;
		case 4:
			return Textures.sOIL_EXTRACTOR_FRONT_ID + view;
		case 5:
			return Textures.sOIL_EXTRACTOR_BACK_ID + view;
		default:
			return Textures.sOIL_EXTRACTOR_BOTTOM_ID + view;
		}
	}

	/**
	 * All sides of the extractor can accept a redstone wire
	 * 
	 * @see net.minecraft.block.Block#canConnectRedstone(net.minecraft.world.IBlockAccess,
	 *      int, int, int, int)
	 */
	@Override
	public boolean canConnectRedstone(IBlockAccess pWorld, int pX, int pY, int pZ, int pSide) {
		return true;
	}

	@Override
	public boolean canPlaceBlockAt(World pWorld, int pX, int pY, int pZ) {
		if (super.canPlaceBlockAt(pWorld, pX, pY, pZ) == false)
			return false;

		/* Check to see if the extractor will be sitting on an oil sands */

		int belowID = pWorld.getBlockId(pX, pY - 1, pZ);
		if ((belowID == Blocks.sOilDirt.blockID) || (belowID == Blocks.sOilSand.blockID))
			return true;
		return false;
	}

	@Override
	public boolean hasTileEntity(int pMetadata) {
		return true;
	}

	/**
	 * @see net.minecraft.block.Block#createTileEntity(net.minecraft.world.World,
	 *      int)
	 */
	@Override
	public TileEntity createTileEntity(World pWorld, int pMetadata) {
		return Machines.createPoweredTileEntity(pWorld, pMetadata);
	}

	/**
	 * @see net.minecraft.block.Block#getTextureFile()
	 */
	@Override
	public String getTextureFile() {
		return Textures.sBLOCK_TEXTURES;
	}
}
