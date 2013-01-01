package petrolcraft;

import java.util.logging.LogManager;
import java.util.logging.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = "PetrolCraft", name = "PetrolCraft", version = "0.0.1")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class Mod_PetrolCraft {

	public static final String sVersion = "0.0.1";

	private static final Logger sLog = Logger.getLogger("PetrolCraft");

	static {

		/* See if the ForgeModLoader logger is present. If so, hook up to it */

		Logger parent = LogManager.getLogManager().getLogger("ForgeModLoader");
		if (parent != null)
			sLog.setParent(parent);
	}

	@Init
	public void load(cpw.mods.fml.common.event.FMLInitializationEvent pEvent) {
		sLog.info("Initialized");
	}

}
