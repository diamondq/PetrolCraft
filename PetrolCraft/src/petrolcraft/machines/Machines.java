package petrolcraft.machines;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.ItemBlock;
import petrolcraft.Mod_PetrolCraft;

public class Machines {
	public static OilExtractorBlock sOilExtractor;

	private static final int sDEFAULT_MACHINE_BLOCK_START_ID = 1418;

	public static void init() {

		/* Oil infused Dirt */

		sOilExtractor = new OilExtractorBlock(Mod_PetrolCraft.sConfig.getBlock("Oil_Extractor_Block_ID",
				sDEFAULT_MACHINE_BLOCK_START_ID + 1, "Oil Extractor Block ID").getInt());
		GameRegistry.registerTileEntity(OilExtractorTileEntity.class, sOilExtractor.getBlockName());
		GameRegistry.registerBlock(sOilExtractor, ItemBlock.class, sOilExtractor.getBlockName(), Mod_PetrolCraft.sMOD_ID);
		LanguageRegistry.addName(sOilExtractor, "Oil Extractor");
	}
}