package petrolcraft.blocks;

import petrolcraft.Mod_PetrolCraft;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.ItemBlock;

public class Blocks {
	public static OilDirt sOilDirt;

	private static final int sDEFAULT_BLOCK_START_ID = 1318;

	public static void init() {

		/* Oil infused Dirt */

		sOilDirt = new OilDirt(Mod_PetrolCraft.sConfig.getBlock("Blocks", "Oil_Dirt_Block_ID", sDEFAULT_BLOCK_START_ID + 1,
				"Oil infused Dirt Block ID").getInt());
		GameRegistry.registerBlock(sOilDirt, ItemBlock.class, sOilDirt.getBlockName(), Mod_PetrolCraft.sMOD_ID);
		LanguageRegistry.addName(sOilDirt, "Oil infused Dirt");
	}
}
