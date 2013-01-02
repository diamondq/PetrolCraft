package petrolcraft.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import petrolcraft.common.Textures;

public class OilSand extends AbstractOilSand {

	public OilSand(int pBlockID) {
		super(pBlockID, Textures.sOIL_SAND_TEXTURE_ID, Material.ground);
		setBlockName("OilSand");
		setResistance(0F);
		setHardness(0.5F);
		setCreativeTab(CreativeTabs.tabBlock);
		setStepSound(soundSandFootstep);
	}
}
