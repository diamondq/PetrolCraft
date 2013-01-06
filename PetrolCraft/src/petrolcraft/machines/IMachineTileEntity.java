package petrolcraft.machines;

import java.io.IOException;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import petrolcraft.common.PetrolUpdatePacket;
import petrolcraft.power.IEnergyConsumer;

public interface IMachineTileEntity {

	/**
	 * Reads a tile entity from NBT.
	 */
	public void readFromNBT(NBTTagCompound par1NBTTagCompound);

	/**
	 * Writes a tile entity to NBT.
	 */
	public void writeToNBT(NBTTagCompound par1NBTTagCompound);

	public TileEntity getActualTileEntity();

	/**
	 * Allows the entity to update its state. Overridden in most subclasses,
	 * e.g. the mob spawner uses this to count ticks and creates a new spawn
	 * inside its implementation.
	 */
	public void updateEntity();

	/**
	 * Called when an the contents of an Inventory change, usually
	 */
	public void onInventoryChanged();

	/**
	 * Determines if this TileEntity requires update calls.
	 * 
	 * @return True if you want updateEntity() to be called, false if not
	 */
	public boolean canUpdate();

	public IEnergyConsumer getEnergyConsumer();

	public int getMetadata();

	public void writeUpdate(PetrolUpdatePacket pUpdatePacket) throws IOException;

	public void processUpdate(PetrolUpdatePacket pUpdatePacket) throws IOException;
}
