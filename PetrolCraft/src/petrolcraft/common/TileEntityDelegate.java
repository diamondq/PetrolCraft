package petrolcraft.common;

import net.minecraft.block.Block;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityDelegate extends TileEntity {

	private TileEntity mDelegate;

	public TileEntityDelegate(TileEntity pDelegate) {
		super();
		mDelegate = pDelegate;

		worldObj = pDelegate.worldObj;
		xCoord = pDelegate.xCoord;
		yCoord = pDelegate.yCoord;
		zCoord = pDelegate.zCoord;
		blockMetadata = pDelegate.blockMetadata;
		blockType = pDelegate.blockType;

	}

	public void updateInternals() {
		worldObj = mDelegate.worldObj;
		xCoord = mDelegate.xCoord;
		yCoord = mDelegate.yCoord;
		zCoord = mDelegate.zCoord;
		blockMetadata = mDelegate.blockMetadata;
		blockType = mDelegate.blockType;
	}

	public int hashCode() {
		return mDelegate.hashCode();
	}

	public World getWorldObj() {
		return mDelegate.getWorldObj();
	}

	public void setWorldObj(World pPar1World) {
		mDelegate.setWorldObj(pPar1World);
		worldObj = mDelegate.worldObj;
	}

	public boolean func_70309_m() {
		return mDelegate.func_70309_m();
	}

	public void readFromNBT(NBTTagCompound pPar1nbtTagCompound) {
		mDelegate.readFromNBT(pPar1nbtTagCompound);
	}

	public void writeToNBT(NBTTagCompound pPar1nbtTagCompound) {
		mDelegate.writeToNBT(pPar1nbtTagCompound);
	}

	public void updateEntity() {
		mDelegate.updateEntity();
	}

	public boolean equals(Object pObj) {
		return mDelegate.equals(pObj);
	}

	public int getBlockMetadata() {
		int result = mDelegate.getBlockMetadata();
		blockMetadata = result;
		return result;
	}

	public void onInventoryChanged() {
		mDelegate.onInventoryChanged();
	}

	public double getDistanceFrom(double pPar1, double pPar3, double pPar5) {
		return mDelegate.getDistanceFrom(pPar1, pPar3, pPar5);
	}

	public double func_82115_m() {
		return mDelegate.func_82115_m();
	}

	public Block getBlockType() {
		blockType = mDelegate.getBlockType();
		return blockType;
	}

	public Packet getDescriptionPacket() {
		return mDelegate.getDescriptionPacket();
	}

	public boolean isInvalid() {
		return mDelegate.isInvalid();
	}

	public void invalidate() {
		mDelegate.invalidate();
	}

	public void validate() {
		mDelegate.validate();
	}

	public void receiveClientEvent(int pPar1, int pPar2) {
		mDelegate.receiveClientEvent(pPar1, pPar2);
	}

	public void updateContainingBlockInfo() {
		mDelegate.updateContainingBlockInfo();
		blockType = mDelegate.blockType;
		blockMetadata = mDelegate.blockMetadata;
	}

	public void func_85027_a(CrashReportCategory pPar1CrashReportCategory) {
		mDelegate.func_85027_a(pPar1CrashReportCategory);
	}

	public boolean canUpdate() {
		return mDelegate.canUpdate();
	}

	public void onDataPacket(INetworkManager pNet, Packet132TileEntityData pPkt) {
		mDelegate.onDataPacket(pNet, pPkt);
	}

	public String toString() {
		return mDelegate.toString();
	}

	public void onChunkUnload() {
		mDelegate.onChunkUnload();
	}

	public boolean shouldRefresh(int pOldID, int pNewID, int pOldMeta, int pNewMeta, World pWorld, int pX, int pY, int pZ) {
		return mDelegate.shouldRefresh(pOldID, pNewID, pOldMeta, pNewMeta, pWorld, pX, pY, pZ);
	}

}
