package petrolcraft;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.FMLRelaunchLog;

import java.lang.reflect.Field;
import java.util.logging.Handler;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import net.minecraftforge.common.Configuration;
import petrolcraft.blocks.Blocks;
import petrolcraft.common.Constants;
import petrolcraft.common.DebugLogFormatter;
import petrolcraft.common.PacketHandler;
import petrolcraft.common.Textures;
import petrolcraft.generation.OnChunkCreation;
import petrolcraft.machines.Machines;

@Mod(modid = "PetrolCraft", name = "PetrolCraft", version = "0.0.1")
@NetworkMod(channels = { Constants.sCHANNEL_NAME }, packetHandler = PacketHandler.class, clientSideRequired = true, serverSideRequired = true)
public class Mod_PetrolCraft {

	public static final String sVersion = "0.0.1";

	private static final Logger sLog = Logger.getLogger("PetrolCraft");

	public static final String sMOD_ID = "PetrolCraft";

	public static Configuration sConfig;

	@SidedProxy(clientSide = "petrolcraft.client.ClientTextureProxy", serverSide = "petrolcraft.common.Textures")
	public static Textures sTextures;

	static {

		/* See if the ForgeModLoader logger is present. If so, hook up to it */

		Logger parent = LogManager.getLogManager().getLogger("ForgeModLoader");
		if (parent != null)
			sLog.setParent(parent);

		/* For debug purposes, update the logging formatter */

		try {
			Field field = FMLRelaunchLog.class.getDeclaredField("myLog");
			field.setAccessible(true);
			Logger logger = (Logger) field.get(FMLRelaunchLog.log);
			Handler[] handlers = logger.getHandlers();
			for (Handler h : handlers) {
				h.setFormatter(new DebugLogFormatter());
			}
		} catch (SecurityException ex) {
			ex.printStackTrace();
		} catch (NoSuchFieldException ex) {
			ex.printStackTrace();
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		}
	}

	@PreInit
	public void preInit(FMLPreInitializationEvent pEvent) {
		setupConfiguration(pEvent);
	}

	@Init
	public void load(cpw.mods.fml.common.event.FMLInitializationEvent pEvent) {
		sLog.info("Initializing");

		sTextures.setupRender();

		setupBlocks();
		setupWorldGeneration();

		sConfig.save();
		sLog.info("Initialized");
	}

	private void setupConfiguration(FMLPreInitializationEvent pEvent) {

		/* Load the configuration */

		sConfig = new Configuration(pEvent.getSuggestedConfigurationFile());

		/* Define any additional comments */

		sConfig.addCustomCategoryComment(Configuration.CATEGORY_BLOCK,
				"This section defines all information about the new blocks that are added to the game");
		sConfig.addCustomCategoryComment("generation", "This section defines how the world generation occurs");
	}

	private void setupBlocks() {
		Blocks.init();
		Machines.init();
	}

	/**
	 * This method is responsible to set up all the world generation
	 * capabilities
	 */
	private void setupWorldGeneration() {
		GameRegistry.registerWorldGenerator(new OnChunkCreation());
	}

}
