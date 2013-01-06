package petrolcraft.machines;

import java.util.logging.Logger;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import petrolcraft.power.IEnergyConsumer;

public class PoweredTileEntity extends TileEntity {

	private static final Logger sLog = Logger.getLogger("PetrolCraft");
	private static final String sClassName = PoweredTileEntity.class.getName();

	protected IMachineTileEntity mActualTileEntity;
	protected IEnergyConsumer mConsumer;

	public PoweredTileEntity(World pWorld, int pMetadata) {
		sLog.entering(sClassName, "PoweredTileEntity", pMetadata);
		setWorldObj(pWorld);
		mActualTileEntity = Machines.createMachineTileEntity(this, pMetadata);
		mConsumer = mActualTileEntity.getEnergyConsumer();
		sLog.exiting(sClassName, "PoweredTileEntity");
	}

	public PoweredTileEntity() {
		sLog.entering(sClassName, "PoweredTileEntity");
		sLog.exiting(sClassName, "PoweredTileEntity");
	}

	public IEnergyConsumer getConsumer() {
		return mConsumer;
	}

	public IMachineTileEntity getMachineTileEntity() {
		return mActualTileEntity;
	}

	/**
	 * @see net.minecraft.tileentity.TileEntity#getDescriptionPacket()
	 */
	@Override
	public Packet getDescriptionPacket() {
		return mConsumer.getDescriptionPacket();
	}

	/**
	 * @see net.minecraft.tileentity.TileEntity#readFromNBT(net.minecraft.nbt.NBTTagCompound)
	 */
	@Override
	public void readFromNBT(NBTTagCompound pPar1nbtTagCompound) {
		sLog.entering(sClassName, "readFromNBT");
		super.readFromNBT(pPar1nbtTagCompound);
		int metadata = pPar1nbtTagCompound.getInteger("metadata");
		mActualTileEntity = Machines.createMachineTileEntity(this, metadata);
		mActualTileEntity.readFromNBT(pPar1nbtTagCompound);
		mConsumer = mActualTileEntity.getEnergyConsumer();
		sLog.exiting(sClassName, "readFromNBT");
	}

	/**
	 * @see net.minecraft.tileentity.TileEntity#writeToNBT(net.minecraft.nbt.NBTTagCompound)
	 */
	@Override
	public void writeToNBT(NBTTagCompound pPar1nbtTagCompound) {
		sLog.entering(sClassName, "writeToNBT");
		super.writeToNBT(pPar1nbtTagCompound);
		pPar1nbtTagCompound.setInteger("metadata", mActualTileEntity.getMetadata());
		mActualTileEntity.writeToNBT(pPar1nbtTagCompound);
		sLog.exiting(sClassName, "writeToNBT");
	}

	/**
	 * @see net.minecraft.tileentity.TileEntity#updateEntity()
	 */
	@Override
	public void updateEntity() {
		mActualTileEntity.updateEntity();
	}

	/**
	 * @see net.minecraft.tileentity.TileEntity#onInventoryChanged()
	 */
	@Override
	public void onInventoryChanged() {
		mActualTileEntity.onInventoryChanged();
	}

	/**
	 * @see net.minecraft.tileentity.TileEntity#canUpdate()
	 */
	@Override
	public boolean canUpdate() {
		return mActualTileEntity.canUpdate();
	}
}
