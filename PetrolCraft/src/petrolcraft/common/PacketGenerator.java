package petrolcraft.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import petrolcraft.machines.IMachineTileEntity;

public class PacketGenerator {

	public static Packet buildUpdatePacket(IMachineTileEntity pTileEntity) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		try {
			dos.writeInt(1);
			PetrolUpdatePacket updatePacket = new PetrolUpdatePacket(dos, pTileEntity.getActualTileEntity());
			pTileEntity.writeUpdate(updatePacket);
			dos.close();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
		byte[] data = baos.toByteArray();
		return new Packet250CustomPayload(Constants.sCHANNEL_NAME, data);
	}

	public static PetrolPacket parsePacket(Packet250CustomPayload pPacket) {
		DataInputStream data = new DataInputStream(new ByteArrayInputStream(pPacket.data));
		try {
			int packetID = data.readInt();
			switch (packetID) {
			case 1:
				return new PetrolUpdatePacket(data);
			}
			return null;
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
}
