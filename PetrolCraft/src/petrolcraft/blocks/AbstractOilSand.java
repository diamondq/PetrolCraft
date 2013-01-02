package petrolcraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import petrolcraft.common.Textures;

public abstract class AbstractOilSand extends Block {

	public AbstractOilSand(int pBlockID, int pTextureID, Material pMaterial) {
		super(pBlockID, pTextureID, pMaterial);
	}

	/**
	 * @see net.minecraft.block.Block#getTextureFile()
	 */
	@Override
	public String getTextureFile() {
		return Textures.sBLOCK_TEXTURES;
	}

}
