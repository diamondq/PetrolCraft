package petrolcraft.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import petrolcraft.common.Textures;

public class OilDirt extends AbstractOilSand {

	public OilDirt(int pBlockID) {
		super(pBlockID, Textures.sOIL_DIRT_TEXTURE_ID, Material.ground);
		setBlockName("OilDirt");
		setResistance(0F);
		setHardness(0.5F);
		setCreativeTab(CreativeTabs.tabBlock);
		setStepSound(soundGravelFootstep);
	}
}
