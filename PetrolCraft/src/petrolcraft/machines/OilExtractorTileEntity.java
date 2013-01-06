package petrolcraft.machines;

import cpw.mods.fml.common.FMLCommonHandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import petrolcraft.common.PacketGenerator;
import petrolcraft.common.PetrolUpdatePacket;
import petrolcraft.power.IEnergyConsumer;

public class OilExtractorTileEntity implements IEnergyConsumer, IMachineTileEntity {

	private static final Logger sLog = Logger.getLogger("PetrolCraft");
	private static final String sClassName = OilExtractorTileEntity.class.getName();

	private int mStoredEnergy = 0;
	private int mMaxEnergy = 3000;
	private TileEntity mTileEntity;

	public OilExtractorTileEntity(TileEntity pTileEntity) {
		sLog.entering(sClassName, "OilExtractorTileEntity");
		mTileEntity = pTileEntity;
		sLog.exiting(sClassName, "OilExtractorTileEntity");
	}

	@Override
	public int getMetadata() {
		return 0;
	}

	public TileEntity getActualTileEntity() {
		return mTileEntity;
	}

	@Override
	public void writeUpdate(PetrolUpdatePacket pPacket) throws IOException {
		DataOutputStream stream = pPacket.getWriteStream();
		stream.writeInt(mStoredEnergy);
		stream.writeInt(mMaxEnergy);
	}

	@Override
	public void processUpdate(PetrolUpdatePacket pUpdatePacket) throws IOException {
		sLog.entering(sClassName, "processUpdate", FMLCommonHandler.instance().getEffectiveSide());
		DataInputStream stream = pUpdatePacket.getReadStream();
		mStoredEnergy = stream.readInt();
		mMaxEnergy = stream.readInt();
		mTileEntity.getWorldObj().markBlockForUpdate(mTileEntity.xCoord, mTileEntity.yCoord, mTileEntity.zCoord);
	}

	/**
	 * @see petrolcraft.power.IEnergyConsumer#getDescriptionPacket()
	 */
	@Override
	public Packet getDescriptionPacket() {
		sLog.entering(sClassName, "getDescriptionPacket");
		return PacketGenerator.buildUpdatePacket(this);
	}

	/**
	 * We'll accept energy from any direction
	 * 
	 * @see petrolcraft.power.IEnergyConsumer#acceptEnergyFrom(net.minecraft.tileentity.TileEntity,
	 *      net.minecraftforge.common.ForgeDirection)
	 */
	@Override
	public boolean acceptEnergyFrom(TileEntity pEntity, ForgeDirection pDirection) {
		// sLog.entering(sClassName, "acceptEnergyFrom", pDirection);
		boolean result = true;
		// sLog.exiting(sClassName, "acceptEnergyFrom", result);
		return result;
	}

	/**
	 * Return the amount of energy room left in the internal batteries
	 * 
	 * @see petrolcraft.power.IEnergyConsumer#getAcceptedEnergyAmount()
	 */
	@Override
	public int getAcceptedEnergyAmount() {
		// sLog.entering(sClassName, "getAcceptedEnergyAmount");
		int result = mMaxEnergy - mStoredEnergy;
		// sLog.exiting(sClassName, "getAcceptedEnergyAmount", result);
		return result;
	}

	/**
	 * Accept energy from the connected power source
	 * 
	 * @see petrolcraft.power.IEnergyConsumer#addEnergy(net.minecraftforge.common.ForgeDirection,
	 *      int)
	 */
	@Override
	public int addEnergy(ForgeDirection pForgeDirection, int pEnergy) {
		sLog.entering(sClassName, "addEnergy", new Object[] { pForgeDirection.name(), pEnergy, mTileEntity.getWorldObj().isRemote });
		int consumable = Math.min(pEnergy, mMaxEnergy - mStoredEnergy);
		mStoredEnergy += consumable;
		mTileEntity.getWorldObj().markBlockForUpdate(mTileEntity.xCoord, mTileEntity.yCoord, mTileEntity.zCoord);
		if ((consumable > 0) && (mStoredEnergy == mMaxEnergy))
			System.out.println("Extractor energy full");
		int result = pEnergy - consumable;
		sLog.exiting(sClassName, "addEnergy", result);
		return result;
	}

	/**
	 * Return the amount of power that we can accept. Currently, we'll accept
	 * any power
	 * 
	 * @see petrolcraft.power.IEnergyConsumer#getMaxSafeInput()
	 */
	@Override
	public int getMaxSafeInput() {
		sLog.entering(sClassName, "getMaxSafeInput");
		return Integer.MAX_VALUE;
	}

	@Override
	public void readFromNBT(NBTTagCompound pTags) {
		mMaxEnergy = pTags.getInteger("pc_maxEnergy");
		mStoredEnergy = pTags.getInteger("pc_storedEnergy");
	}

	@Override
	public void writeToNBT(NBTTagCompound pTags) {
		pTags.setInteger("pc_maxEnergy", mMaxEnergy);
		pTags.setInteger("pc_storedEnergy", mStoredEnergy);
	}

	@Override
	public void updateEntity() {

	}

	@Override
	public void onInventoryChanged() {

	}

	@Override
	public boolean canUpdate() {
		return true;
	}

	@Override
	public IEnergyConsumer getEnergyConsumer() {
		return this;
	}

	@Override
	public int getMaxStorage() {
		return mMaxEnergy;
	}

}
