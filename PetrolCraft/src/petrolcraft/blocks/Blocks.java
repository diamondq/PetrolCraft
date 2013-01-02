package petrolcraft.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.ItemBlock;
import petrolcraft.Mod_PetrolCraft;

public class Blocks {
	public static OilDirt sOilDirt;
	public static OilSand sOilSand;

	private static final int sDEFAULT_BLOCK_START_ID = 1318;

	public static void init() {

		/* Oil infused Dirt */

		sOilDirt = new OilDirt(Mod_PetrolCraft.sConfig.getBlock("Oil_Dirt_Block_ID", sDEFAULT_BLOCK_START_ID + 1,
				"Oil infused Dirt Block ID").getInt());
		GameRegistry.registerBlock(sOilDirt, ItemBlock.class, sOilDirt.getBlockName(), Mod_PetrolCraft.sMOD_ID);
		LanguageRegistry.addName(sOilDirt, "Oil infused Dirt");
		
		/* Oil infused Sand */

		sOilSand = new OilSand(Mod_PetrolCraft.sConfig.getBlock("Oil_Sand_Block_ID", sDEFAULT_BLOCK_START_ID + 2,
				"Oil infused Sand Block ID").getInt());
		GameRegistry.registerBlock(sOilSand, ItemBlock.class, sOilSand.getBlockName(), Mod_PetrolCraft.sMOD_ID);
		LanguageRegistry.addName(sOilSand, "Oil infused Sand");
		
	}
}
