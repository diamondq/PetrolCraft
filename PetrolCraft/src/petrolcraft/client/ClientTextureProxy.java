package petrolcraft.client;

import net.minecraftforge.client.MinecraftForgeClient;
import petrolcraft.common.Textures;

public class ClientTextureProxy extends Textures {

	@Override
	public void setupRender() {
		MinecraftForgeClient.preloadTexture(sBLOCK_TEXTURES);
	}
}
