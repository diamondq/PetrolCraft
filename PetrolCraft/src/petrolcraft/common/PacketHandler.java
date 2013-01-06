package petrolcraft.common;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import petrolcraft.machines.PoweredTileEntity;

public class PacketHandler implements IPacketHandler {

	public PacketHandler() {
	}

	@Override
	public void onPacketData(INetworkManager pManager, Packet250CustomPayload pPacket, Player pPlayer) {
		PetrolPacket packet = PacketGenerator.parsePacket(pPacket);
		if (packet instanceof PetrolUpdatePacket) {
			PetrolUpdatePacket p = (PetrolUpdatePacket) packet;
			World world = ((EntityPlayer) pPlayer).worldObj;
			int x = p.getX();
			int y = p.getY();
			int z = p.getZ();
			if (!world.blockExists(x, y, z))
				return;

			TileEntity entity = world.getBlockTileEntity(x, y, z);
			if (!(entity instanceof PoweredTileEntity))
				return;

			PoweredTileEntity poweredEntity = (PoweredTileEntity) entity;

			try {
				poweredEntity.getMachineTileEntity().processUpdate(p);
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}

		}
	}

}
