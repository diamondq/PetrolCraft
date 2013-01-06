package petrolcraft.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.tileentity.TileEntity;

public class PetrolUpdatePacket extends PetrolPacket {

	private int mX;
	private int mY;
	private int mZ;
	private DataInputStream mReadStream;
	private DataOutputStream mWriteStream;

	public PetrolUpdatePacket(DataInputStream pData) throws IOException {
		mReadStream = pData;
		mX = pData.readInt();
		mY = pData.readInt();
		mZ = pData.readInt();
	}

	public PetrolUpdatePacket(DataOutputStream pData, TileEntity pEntity) throws IOException {
		mWriteStream = pData;
		mX = pEntity.xCoord;
		mY = pEntity.yCoord;
		mZ = pEntity.zCoord;
		pData.writeInt(mX);
		pData.writeInt(mY);
		pData.writeInt(mZ);
	}

	public int getX() {
		return mX;
	}

	public int getY() {
		return mY;
	}

	public int getZ() {
		return mZ;
	}

	public DataOutputStream getWriteStream() {
		return mWriteStream;
	}

	public DataInputStream getReadStream() {
		return mReadStream;
	}

}
