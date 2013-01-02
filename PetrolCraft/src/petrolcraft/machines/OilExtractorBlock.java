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
		super(pBlockID, 1, Material.ground);
		setBlockName("OilExtractor");
		setResistance(0F);
		setHardness(0.5F);
		setCreativeTab(CreativeTabs.tabTools);
		setStepSound(soundMetalFootstep);
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
		if (belowID == Blocks.sOilDirt.blockID)
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
		return new OilExtractorTileEntity(pWorld, pMetadata);
	}

	/**
	 * @see net.minecraft.block.Block#getTextureFile()
	 */
	@Override
	public String getTextureFile() {
		return Textures.sBLOCK_TEXTURES;
	}
}
