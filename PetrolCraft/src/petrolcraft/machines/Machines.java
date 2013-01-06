package petrolcraft.machines;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import petrolcraft.Mod_PetrolCraft;
import petrolcraft.power.bc.BCPoweredTileEntity;

public class Machines {
	public static OilExtractorBlock sOilExtractor;

	private static final int sDEFAULT_MACHINE_BLOCK_START_ID = 1418;

	public static void init() {

		/* Oil infused Dirt */

		sOilExtractor = new OilExtractorBlock(Mod_PetrolCraft.sConfig.getBlock("Oil_Extractor_Block_ID",
				sDEFAULT_MACHINE_BLOCK_START_ID + 1, "Oil Extractor Block ID").getInt());
		GameRegistry.registerTileEntity(getPoweredTileEntityClass(), sOilExtractor.getBlockName());
		GameRegistry.registerBlock(sOilExtractor, ItemBlock.class, sOilExtractor.getBlockName(), Mod_PetrolCraft.sMOD_ID);
		ClientRegistry.bindTileEntitySpecialRenderer(getPoweredTileEntityClass(), new OilExtractorRenderer());
		LanguageRegistry.addName(sOilExtractor, "Oil Extractor");
	}

	public static Class<? extends TileEntity> getPoweredTileEntityClass() {
		return BCPoweredTileEntity.class;
	}

	public static PoweredTileEntity createPoweredTileEntity(World pWorld, int pMetadata) {
		return new BCPoweredTileEntity(pWorld, pMetadata);
	}

	public static IMachineTileEntity createMachineTileEntity(TileEntity pTileEntity, int pMetadata) {
		return new OilExtractorTileEntity(pTileEntity);
	}
}