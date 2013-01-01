package petrolcraft;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

import java.io.File;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import net.minecraftforge.common.Configuration;
import petrolcraft.blocks.Blocks;
import petrolcraft.generation.OnChunkCreation;

@Mod(modid = "PetrolCraft", name = "PetrolCraft", version = "0.0.1")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class Mod_PetrolCraft {

	public static final String sVersion = "0.0.1";

	private static final Logger sLog = Logger.getLogger("PetrolCraft");

	public static final String sMOD_ID = "PetrolCraft";

	public static Configuration sConfig;

	static {

		/* See if the ForgeModLoader logger is present. If so, hook up to it */

		Logger parent = LogManager.getLogManager().getLogger("ForgeModLoader");
		if (parent != null)
			sLog.setParent(parent);
	}

	@Init
	public void load(cpw.mods.fml.common.event.FMLInitializationEvent pEvent) {
		sLog.info("Initializing");

		setupConfiguration();
		setupBlocks();
		setupWorldGeneration();

		sConfig.save();
		sLog.info("Initialized");
	}

	private void setupConfiguration() {

		/* Load the configuration */

		sConfig = new Configuration(new File(Loader.instance().getConfigDir(), "mod_PetrolCraft.cfg"), true);

		/* Define any additional comments */

		sConfig.addCustomCategoryComment("Blocks", "This section defines all information about the new blocks that are added to the game");
		sConfig.addCustomCategoryComment("Generation", "This section defines how the world generation occurs");
	}

	private void setupBlocks() {
		Blocks.init();
	}

	/**
	 * This method is responsible to set up all the world generation
	 * capabilities
	 */
	private void setupWorldGeneration() {
		GameRegistry.registerWorldGenerator(new OnChunkCreation());
	}

}
