package petrolcraft.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class OilDirt extends AbstractOilSand {

	public OilDirt(int pBlockID) {
		super(pBlockID, 24, Material.ground);
		setBlockName("OilDirt");
		setResistance(0F);
		setHardness(0.5F);
		setCreativeTab(CreativeTabs.tabBlock);
		setStepSound(soundGravelFootstep);
	}
}
